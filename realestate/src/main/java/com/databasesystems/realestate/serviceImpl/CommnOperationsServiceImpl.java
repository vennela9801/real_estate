package com.databasesystems.realestate.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.databasesystems.realestate.entity.AddressEntity;
import com.databasesystems.realestate.entity.Agent;
import com.databasesystems.realestate.entity.HomeEntity;
import com.databasesystems.realestate.entity.HomeOwnerEntity;
import com.databasesystems.realestate.models.request.SearchRequest;
import com.databasesystems.realestate.models.response.SearchInquiryResponse;
import com.databasesystems.realestate.repository.AddressRepository;
import com.databasesystems.realestate.repository.AgentRepository;
import com.databasesystems.realestate.repository.HomeOwnersRepository;
import com.databasesystems.realestate.repository.HomeRepository;
import com.databasesystems.realestate.service.CommnOperationsService;

@Service
public class CommnOperationsServiceImpl implements CommnOperationsService {

	@Autowired
	AgentRepository agentRepo;

	@Autowired
	HomeRepository homeRepo;
	
	@Autowired
	HomeOwnersRepository homeOwnersRepo;

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	JdbcTemplate template;

	@Override
	public Agent createAgent(Agent agent) throws Exception {
		// TODO Auto-generated method stub
		try {
			return agentRepo.save(agent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<SearchInquiryResponse> searchInquiry(SearchRequest request) throws Exception {
		// TODO Auto-generated method stub
		try {
			// 1) List all the homes owned by a given owner in a given city.
			if (!StringUtils.isEmpty(request.getHouseOwner()) && !StringUtils.isEmpty(request.getCity())) {
				String queryStr = "Select home.HomeID as houseId, home.owner as houseOwner,home.FloorSpace as floorSpace, home.Floors as floors, home.Bathrooms as bathrooms, home.LandSize as landSize,\r\n"
						+ " home.YearConstructed, home.HomeType as houseType, home.Bedrooms as bedrooms, \r\n"
						+ "  address.address as address, address.city as city, address.county as county, address.zip as zipCode,address.state, home.rate as price \r\n"
						+ "From home, address \r\n" + "Where home.Address_id = address.Address_id and  \r\n"
						+ "home.owner = ? and \r\n" + "address.city = ?;";
				List<String> params = new ArrayList<>();
				params.add(request.getHouseOwner());
				params.add(request.getCity());
				List<SearchInquiryResponse> resp = template.query(queryStr, params.toArray(),
						new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
				return resp;
			} // 2) List all the homes that were sold more than once.
			else if ("CONDITION1".equalsIgnoreCase(request.getCondition1())
					&& !StringUtils.isEmpty(request.getCountofTimesHouseSold())) {
				String queryStr = "Select DISTINCT(home.HomeID) as houseId, ho.OwnerFName as ownerFirstName, ho.OwnerLName as ownerLastName,home.YearConstructed as yearConstructed, home.HomeType as houseType, home.availabilityStatus as status, home.rate as price\r\n"
						+ "						From home, homeowners ho, listing li Where (\r\n"
						+ "						li.HomeID = home.HomeID and  li.OwnerID = ho.OwnerID and \r\n"
						+ "						li.status = 'Sold' and \r\n"
						+ "						home.HomeID in (select HomeID from listing group by HomeID having count(HomeID)>?));";
				List<String> params = new ArrayList<>();
				params.add(request.getCountofTimesHouseSold() != 0 ? String.valueOf(request.getCountofTimesHouseSold())
						: String.valueOf(1));
				List<SearchInquiryResponse> resp = template.query(queryStr, params.toArray(),
						new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
				return resp;
			} // 3) Find the most expensive home an owner ever bought.
			else if ("CONDITION2".equalsIgnoreCase(request.getCondition1())
					&& !StringUtils.isEmpty(request.getHouseOwner())) {
				String queryStr = "Select DISTINCT(home.HomeID) as houseId, ho.OwnerFName as ownerFirstName, ho.OwnerLName as ownerLastName, listing.price, home.HomeType as houseType, listing.status,home.YearConstructed as yearConstructed \r\n"
						+ "From home,  homeowners ho, listing \r\n" + "Where \r\n"
						+ "listing.HomeID = home.HomeID and \r\n" + "listing.OwnerID = ho.OwnerID and \r\n"
						+ "listing.price in (select max(price) from listing where OwnerId = ho.OwnerID) and ho.OwnerFName = ?"
						+ " and ho.OwnerLName = ?;";

				List<String> params = new ArrayList<>();
				String arr[] = request.getHouseOwner().split(" ", 2);
				params.add(arr[0]);
				params.add(arr.length > 1 ? arr[1] : "");
				List<SearchInquiryResponse> resp = template.query(queryStr, params.toArray(),
						new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
				return resp;
			} // 4) Find all the homes that include all the appliances by the same maker.
			else if ("CONDITION3".equalsIgnoreCase(request.getCondition1())) {
				String queryStr = "SELECT ap.MakeYear as makeYear,ap.ApplianceName as applianceName,h.owner as houseOwner, h.homeType as houseType, h.yearConstructed as yearConstructed, h.rate as price FROM appliances ap, home h \r\n"
						+ "WHERE ap.HomeID IN ( SELECT HomeID FROM appliances ap GROUP BY HomeID HAVING COUNT(DISTINCT ap.maker)>1 ) and h.homeID = ap.homeId;";

				List<SearchInquiryResponse> resp = template.query(queryStr,
						new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
				return resp;
			} // 5) Find previous home owners for sold homes.
			else if ("CONDITION4".equalsIgnoreCase(request.getCondition1())) {
				String queryStr = "Select  HO.OwnerFName as ownerFirstName, HO.OwnerLName as ownerLastName,Home.homeType as houseType,Home.yearConstructed as yearConstructed, Address.address, Address.city, Address.state, Address.zip as zipCode,Listing.price, Listing.Status\r\n"
						+ "From HomeOwners HO, Listing, Home, Address \r\n"
						+ "Where (Listing.OwnerID = HO.OwnerID and\r\n" + "Listing.HomeID = Home.HomeID and \r\n"
						+ "Home.Address_ID = Address.Address_ID and \r\n" + "Listing.Status = 'Sold');";

				List<SearchInquiryResponse> resp = template.query(queryStr,
						new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
				return resp;
			}
			// 6) Find the total commissions earned by an agent. Assume that commission
			// earned is on the purchased price of a home he/she sells.
			else if ("CONDITION5".equalsIgnoreCase(request.getCondition1())
					&& !StringUtils.isEmpty(request.getAgentID())) {
				String queryStr = "SELECT Agent.AgentID, AgentFName as agentFirstName, AgentLName as agentLastName, round(sum(listing.price * (listing.CommissionRate/100)),2) AS totalCommission \r\n"
						+ "FROM listing, agent  \r\n" + "where listing.agentid = agent.agentid and\r\n"
						+ "listing.agentID = ? ;";

				List<String> params = new ArrayList<>();
				params.add(request.getAgentID());
				List<SearchInquiryResponse> resp = template.query(queryStr, params.toArray(),
						new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
				return resp;
			} // people who own apartments and mansions
			else if ("CONDITION6".equalsIgnoreCase(request.getCondition1())) {
				// TO DO
			} // ppl with most expensive homes in a city
			else if ("CONDITION7".equalsIgnoreCase(request.getCondition1())
					&& !StringUtils.isEmpty(request.getCity())) {
				// TO DO
				String queryStr = "SELECT distinct ho.OwnerFName as ownerFirstName,ho.OwnerLName as ownerLastName,ls.price, h.homeType as houseType, h.yearConstructed as yearConstructed, ad.address, ad.city, ad.state, ad.zip as zipCode, h.availabilityStatus as status FROM homeowners ho\r\n"
						+ "					JOIN listing ls ON ls.OwnerID = ho.OwnerID\r\n"
						+ "					JOIN home h ON h.HomeID = ls.HomeID\r\n"
						+ "					JOIN address ad ON ad.Address_ID = h.Address_ID\r\n"
						+ "					where ad.city = ? and UPPER(ls.status) = 'AVAILABLE'\r\n" + "					order by ls.price desc\r\n"
						+ "					limit 10;";

				List<String> params = new ArrayList<>();
				params.add(request.getCity());
				List<SearchInquiryResponse> resp = template.query(queryStr, params.toArray(),
						new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
				return resp;
			} else if ("CONDITION8".equalsIgnoreCase(request.getCondition1()) && !StringUtils.isEmpty(request.getCity())
					&& !StringUtils.isEmpty(request.getPriceScale())) {
				// TO DO
				String queryStr = "SELECT distinct home.hometype as houseType, home.HomeID, home.YearConstructed as yearConstructed, listing.price, home.owner as houseOwner, address.address, address.city, address.state, address.zip as zipCode\r\n"
						+ "					FROM Home home\r\n"
						+ "					JOIN Address address ON home.Address_ID = address.Address_ID\r\n"
						+ "					JOIN Listing listing ON home.HomeID = listing.HomeID\r\n"
						+ "					WHERE address.city = ? AND listing.price < ? and UPPER(listing.status) = 'AVAILABLE';";

				List<String> params = new ArrayList<>();
				params.add(request.getCity());
				params.add(request.getPriceScale());
				List<SearchInquiryResponse> resp = template.query(queryStr, params.toArray(),
						new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
				return resp;
			} // homes that are available for sale
			else if ("CONDITION9".equalsIgnoreCase(request.getCondition1())) {
				// TO DO
				String queryStr = "Select distinct home.HomeID as houseId, home.owner as houseOwner,home.FloorSpace as floorSpace, home.Floors as floors, home.Bedrooms as bedrooms, home.Bathrooms as bathrooms, home.LandSize as landSize,\r\n"
						+ "					home.YearConstructed, home.HomeType as houseType,\r\n"
						+ "					address.address as address, address.city as city, address.county as county, address.zip as zipCode,address.state, home.rate as price \r\n"
						+ "					From home, address \r\n"
						+ "					Where home.Address_id = address.Address_id  and UPPER(home.availabilityStatus) = 'AVAILABLE' ";

				List<String> params = new ArrayList<>();
				if (!StringUtils.isEmpty(request.getCity())) {
					params.add(request.getCity());
					queryStr = queryStr + "and address.city = ? ";
				}
				if (!StringUtils.isEmpty(request.getFloors()) && 0 != request.getFloors()) {
					params.add(String.valueOf(request.getFloors()));
					queryStr = queryStr + "and home.Floors = ? ";
				}
				if (!StringUtils.isEmpty(request.getBedRoomCount()) && 0 != request.getBedRoomCount()) {
					params.add(String.valueOf(request.getBedRoomCount()));
					queryStr = queryStr + "and home.Bedrooms = ? ";
				}
				if (!StringUtils.isEmpty(request.getTotalbaths())) {
					params.add(request.getTotalbaths());
					queryStr = queryStr + "and home.Bathrooms = ? ";
				}
				queryStr = queryStr + ";";

				List<SearchInquiryResponse> resp = template.query(queryStr, params.toArray(),
						new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
				return resp;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public HomeEntity saveorUpdateHome(HomeEntity home) throws Exception {
		// TODO Auto-generated method stub

		try {
			AddressEntity addressEnt = addressRepo.save(home.getAddress());
			home.setAddress(addressEnt);
			home.setAddressId(addressEnt.getId());
			return homeRepo.save(home);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public HomeEntity fetchHomeDetails(int homeID) throws Exception {
		// TODO Auto-generated method stub
		HomeEntity entity = null;
		try {
			Optional<HomeEntity> homeResp = homeRepo.findById(homeID);
			if (homeResp.isPresent()) {
				entity = homeResp.get();
				Optional<AddressEntity> address = addressRepo.findById(entity.getAddressId());
				entity.setAddress(address.isPresent() ? address.get() : null);
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}

	}

	@Override
	public HomeOwnerEntity saveorUpdateHomeOwners(HomeOwnerEntity homeOwner) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			AddressEntity addressEnt = addressRepo.save(homeOwner.getAddress());
			homeOwner.setAddress(addressEnt);
			homeOwner.setAddressId(addressEnt.getId());
			return homeOwnersRepo.save(homeOwner);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}
	}

	@Override
	public boolean deleteHomeDetails(HomeEntity home) throws Exception {
		// TODO Auto-generated method stub
		try {
			homeRepo.deleteById(home.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}
	}

}
