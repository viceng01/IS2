package com.rainforest.model;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;
import com.rainforest.model.user.buyer.Buyer;
import com.rainforest.model.user.buyer.BuyerInfo;
import com.rainforest.model.user.seller.Seller;

public class RainforestModel implements JSONSerializable {

	private User currentUser;
	private Set<Buyer> buyerSet;
	private Set<Seller> sellerSet;

	public RainforestModel() {
		buyerSet = new HashSet<Buyer>();
		sellerSet = new HashSet<Seller>();
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public boolean addBuyer(Buyer buyer) {
		if (buyer == null)
			return false;

		return buyerSet.add(buyer);
	}

	public boolean addSeller(Seller seller) {
		if (seller == null)
			return false;

		return sellerSet.add(seller);
	}

	public boolean removeUser(GUID guid) {
		boolean removedUser = false;

		removedUser = removedUser || buyerSet.removeIf((b) -> {
			return b.getUserInfo().getUserID().equals(guid);
		});
		removedUser = removedUser || sellerSet.removeIf((s) -> {
			return s.getUserInfo().getUserID().equals(guid);
		});

		return removedUser;
	}

	public LoginResponse tryLoginAsBuyer(String email, String password) {
		return tryLogin(email, password, buyerSet);
	}

	public LoginResponse tryLoginAsSeller(String email, String password) {
		return tryLogin(email, password, sellerSet);
	}

	private LoginResponse tryLogin(String email, String password, Set<? extends User> userSet) {
		for (User u : userSet) {
			UserInfo info = u.getUserInfo();

			if (info.getEmail().equals(email)) {
				if (info.getPassword().equals(password)) {
					currentUser = u;
					return LoginResponse.OK;
				} else
					return LoginResponse.INCORRECT_PASSWORD;
			}
		}

		return LoginResponse.UNKNOWN_USER;
	}

	public boolean doesUserExist(String email) {
		boolean buyerExists = checkForEachBuyer((b) -> {
			return b.getUserInfo().getEmail().equals(email);
		});
		boolean sellerExists = checkForEachSeller((s) -> {
			return s.getUserInfo().getEmail().equals(email);
		});

		return buyerExists || sellerExists;
	}

	public boolean doesBuyerExist(String dni) {
		return checkForEachBuyer((b) -> {
			return b.getBuyerInfo().getDNI().equals(dni);
		});
	}

	public boolean doesSellerExist(String cif) {
		return checkForEachSeller((s) -> {
			return s.getSellerInfo().getCIF().equals(cif);
		});
	}

	public Buyer getBuyerByEmail(String email) {
		for (Buyer buyer : buyerSet)
			if (buyer.getUserInfo().getEmail().equals(email))
				return buyer;

		return null;
	}

	public Buyer getBuyerByDNI(String dni) {
		for (Buyer buyer : buyerSet)
			if (buyer.getBuyerInfo().getDNI().equals(dni))
				return buyer;

		return null;
	}

	public GUID getUserGUIDWithAuthentication(String email, String password) {
		for (Buyer buyer : buyerSet) {
			UserInfo ui = buyer.getUserInfo();

			if (ui.getEmail().equals(email) && ui.getPassword().equals(password))
				return ui.getUserID();
		}

		return null;
	}

	public Seller getSeller(String email) {
		for (Seller seller : sellerSet) {
			if (seller.getUserInfo().getEmail().equals(email))
				return seller;
		}

		return null;
	}

	public ModifyResponse tryModifyBuyer(String email, String username, String password, String address, int telephone,
			String dni) {
		ModifyResponse mr = ModifyResponse.OK;
		Buyer buyer = getBuyerByDNI(dni);

		UserInfo currentUserInfo = buyer.getUserInfo();

		if (!getBuyerByEmail(email).equals(buyer)) {
			mr = ModifyResponse.EMAIL_ALREADY_EXISTS;
		} else
			currentUserInfo.setEmail(email);

		currentUserInfo.setAddress(address);
		currentUserInfo.setPassword(password);
		currentUserInfo.setTelephone(telephone);
		currentUserInfo.setUsername(username);

		return mr;
	}

	public JSONArray getProducts(GUID guid) {
		JSONArray allProducts = new JSONArray();

		for (Seller seller : sellerSet)
			allProducts.put(seller.serializeProducts());

		return allProducts;
	}

//	public void removeProduct(GUID name, String dni) {
//
//		for (User u : userSet) {
//			if (u.canSell()) {
//				if (u.getUserInfo().getDNI().equals(dni)) {// Si encontramos el dni del comprador debemos
//					// buscar el prodcuto en su catalogo y eliminarlo
//					u.removeProduct(name);
//
//				}
//
//			}
//		}
//
//	}

	private boolean checkForEachBuyer(Predicate<Buyer> buyerPredicate) {
		for (Buyer buyer : buyerSet)
			if (buyerPredicate.test(buyer))
				return true;

		return false;
	}

	private boolean checkForEachSeller(Predicate<Seller> sellerPredicate) {
		for (Seller seller : sellerSet)
			if (sellerPredicate.test(seller))
				return true;

		return false;
	}

	@Override
	public JSONObject serialize() {
		JSONObject jo = new JSONObject();

		JSONArray buyerArray = new JSONArray();
		JSONArray sellerArray = new JSONArray();

		buyerSet.forEach((b) -> {
			buyerArray.put(b.serialize());
		});
		sellerSet.forEach((s) -> {
			sellerArray.put(s.serialize());
		});

		jo.put("buyers", buyerArray);
		jo.put("sellers", sellerArray);

		return jo;
	}
}
