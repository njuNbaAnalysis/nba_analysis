package JsonServlet;

import java.rmi.RemoteException;
import java.util.ArrayList;

import logic.BLController;

import org.json.JSONArray;
import org.json.JSONObject;

import util.JSONGenerator;
import vo.EventVo;
import vo.HotZonevo;
import vo.MatchSimpleInfovo;
import vo.Matchvo;
import vo.Playervo;
import vo.Teamvo;
import vo.TodayPlayervo;

public class GetJsonData {
	
	private static GetJsonData getJsonData = null;
	
	private GetJsonData(){}
	
	public static GetJsonData getInstance(){
		if(getJsonData == null){
			getJsonData = new GetJsonData();
		}
		return getJsonData;
	}
	

	public JSONObject initNBALive(){
		boolean result = false;
		try {
			result = BLController.getInstance().initNBALive();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		object.put("result", result);
		return object;
	}

	public JSONObject getLiveMatchInfo(){
		Matchvo result = null;
		try {
			result = BLController.getInstance().getLiveMatchInfo();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "今天没有比赛");
		}
		else{
			object = JSONGenerator.getJSONObject(result);
		}
		return object;
	}

	public JSONObject getLiveEvent(){
		ArrayList<EventVo> result = null;
		try {
			result = BLController.getInstance().getLiveEvent();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "今天没有比赛");
		}
		else if(result.size() == 0){
			object.put("result", "今天已经比赛完了");
		}
		else{
			JSONArray array = new JSONArray();
			for(EventVo vo:result){
				array.put(JSONGenerator.getJSONObject(vo));
			}
			object.put("result", array);
		}
		return object;
	}

	public JSONObject getAllPlayers(String Season, String isPlayOff){
		ArrayList<Playervo> result = null;
		try {
			result = BLController.getInstance().getAllPlayers(Season, Boolean.parseBoolean(isPlayOff));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "该赛季没有球员");
		}
		else if(result.size() == 0){
			object.put("result", "该赛季没有球员");
		}
		else{
			JSONArray array = new JSONArray();
			for(Playervo vo:result){
				array.put(JSONGenerator.getJSONObject(vo));
			}
			object.put("result", array);
		}
		return object;
	}
	
	public JSONObject getSeasonKingPlayer(String transferField,
			String number,String season,String isplayoff){
		ArrayList<Playervo> result = null;
		try {
			result = BLController.getInstance().getSeasonKingPlayer(transferField, Integer.parseInt(number), season, Boolean.parseBoolean(isplayoff));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "该赛季没有球员");
		}
		else if(result.size() == 0){
			object.put("result", "该赛季没有球员");
		}
		else{
			JSONArray array = new JSONArray();
			for(Playervo vo:result){
				array.put(JSONGenerator.getJSONObject(vo));
			}
			object.put("result", array);
		}
		return object;
	}
	
	public JSONObject getTodayKingPlayer(String date,
			String transferField, String number){
		ArrayList<TodayPlayervo> result = null;
		try {
			result = BLController.getInstance().getTodayKingPlayer(date, transferField, Integer.parseInt(number));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "未知错误");
		}
		else if(result.size() == 0){
			object.put("result", "未知错误");
		}
		else{
			JSONArray array = new JSONArray();
			for(TodayPlayervo vo:result){
				array.put(JSONGenerator.getJSONObject(vo));
			}
			object.put("result", array);
		}
		return object;
	}
	
	public JSONObject getPlayerById(String Id){
		Playervo result = null;
		try {
			result = BLController.getInstance().getPlayerById(Id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "未找到该球员");
		}
		else{
			object = JSONGenerator.getJSONObject(result);
		}
		return object;
	}

	public JSONObject getPlayerById(String Id, String season, String isplayoff){
		Playervo result = null;
		try {
			result = BLController.getInstance().getPlayerById(Id, season, Boolean.parseBoolean(isplayoff));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "未找到该球员");
		}
		else{
			object = JSONGenerator.getJSONObject(result);
		}
		return object;
	}
	
	public JSONObject getPlayerByNameAndTeam(String playerName){
		Playervo result = null;
		try {
			result = BLController.getInstance().getPlayerByNameAndTeam(playerName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "未找到该球员");
		}
		else{
			object = JSONGenerator.getJSONObject(result);
		}
		return object;
	}
	
	public JSONObject getAlliancePlayerAverageData(String season,
			String isPlayOff){
		double[] result = null;
		try {
			result = BLController.getInstance().getAlliancePlayerAverageData(season, Boolean.parseBoolean(isPlayOff));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray(result);
		object.put("result", array);
		return object;
	}

	//13-14_2014-05-05
	public JSONObject getTodayMatches(String string){
		ArrayList<Matchvo> result = null;
		try {
			result = (ArrayList<Matchvo>) BLController.getInstance().getTodayMatches(string);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		JSONObject object = new JSONObject();
		if(result == null || result.size() == 0){
			object.put("result", "今天没有比赛");
		}
		else{
			JSONArray array = new JSONArray();
			for(Matchvo vo:result){
				array.put(JSONGenerator.getJSONObject(vo));
			}
			object.put("result", array);
		}
		
		return object; 
	}
	
	public JSONObject getMatchSimpleInfo(String teamName,
			String season){
		ArrayList<MatchSimpleInfovo> result = null;
		try {
			result = BLController.getInstance().getMatchSimpleInfo(teamName, season);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "未知错误");
		}
		else if(result.size() == 0){
			object.put("result", "未知错误");
		}
		else{
			JSONArray array = new JSONArray();
			for(MatchSimpleInfovo vo:result){
				array.put(JSONGenerator.getJSONObject(vo));
			}
			object.put("result", array);
		}
		return object;
	}

	public JSONObject getTeamByPlayerId(String playerId, String season,
			String isPlayOff){
		Teamvo result = null;
		try {
			result = BLController.getInstance().getTeamByPlayerId(playerId, season, Boolean.parseBoolean(isPlayOff));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "未找到该球队");
		}
		else{
			object = JSONGenerator.getJSONObject(result);
		}
		return object;
	}

	public JSONObject getTeamByTeamName(String teamNameEn, String season,
			String isPlayOff){
		Teamvo result = null;
		try {
			result = BLController.getInstance().getTeamByTeamName(teamNameEn, season, Boolean.parseBoolean(isPlayOff));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "未找到该球队");
		}
		else{
			object = JSONGenerator.getJSONObject(result);
		}
		return object;
	}

    public JSONObject getTeamWithLatest10Data(String teamNameEn, String season, String isPlayOff){
    	Teamvo result = null;
		try {
			result = BLController.getInstance().getTeamWithLatest10Data(teamNameEn, season, Boolean.parseBoolean(isPlayOff));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "未找到该球队");
		}
		else{
			object = JSONGenerator.getJSONObject(result);
		}
		return object;
    }
    
	public JSONObject getAllTeams(String Season, String isPlayOff){
		ArrayList<Teamvo> result = null;
		try {
			result = BLController.getInstance().getAllTeams(Season, Boolean.parseBoolean(isPlayOff));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		for(Teamvo token:result){
			array.put(JSONGenerator.getJSONObject(token));
		}
		object.put("teamList", array);
		
		return object;
	}

	public JSONObject getHotZone(String teamNameEn, String isSeason,
			String isTotal){
		HotZonevo result = null;
		try {
			result = BLController.getInstance().getHotZone(teamNameEn, Boolean.parseBoolean(isSeason), Boolean.parseBoolean(isTotal));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result", "未找到该热区信息");
		}
		else{
			object = JSONGenerator.getJSONObject(result);
		}
		return object;
	}

	public JSONObject getTeamAbility(String teamNameEn){ 
		double[] result = null;
		try {
			result = BLController.getInstance().getTeamAbility(teamNameEn);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		if(result == null){
			object.put("result","未知错误");
		}
		else{
			JSONArray array = new JSONArray(result);
			object.put("result", array);
		}
		return object;
	}
	
	
}
