package weibo4j.examples.timeline;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
public class GetHomeTimeline {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	static class MyTask implements Runnable{
		@Override
		public void run() {
			//通过API测试工具获取
			String access_token = "2.00ZTQAvCOrgHnD16b836f56bX5c2ME";
//			access_token = "2.00ZTQAvCFkfoyC9a8070103dvoYVBE";
			access_token = "2.00ZTQAvCwIsxfC1ad6266543CzTmGB";
			Timeline tm = new Timeline();
			tm.client.setToken(access_token);
			System.out.println("running at : "+sdf.format(new Date()));
			try {
				StatusWapper status = tm.getHomeTimeline();

				
				for(Status s : status.getStatuses()){
					                          
					if(s.toString().contains("1767797335")){
						saveMessage(s,"ER");
					}else if(s.toString().contains("1704422861")){
						saveMessage(s,"zheng");
					}else if(s.toString().contains("2074611540")){
						saveMessage(s,"moda");
					}/*else if(s.getUser().getId().equalsIgnoreCase("1645776681")){//LoneSchicksal
						saveMessage(s,"Lone");
					}*/else if(s.getUser().getId().equalsIgnoreCase("1769173661")){//peng
						saveMessage(s,"Peng");
					}else if(s.getUser().getId().equalsIgnoreCase("2703536433")){//gren
						saveMessage(s,"gren");
					}else if(s.getUser().getId().equalsIgnoreCase("2013960857")){//hejia
						saveMessage(s,"hejia");
					}else if(s.getUser().getId().equalsIgnoreCase("2839685750")){//wzq
						saveMessage(s,"wang");
					}else if(s.getUser().getId().equalsIgnoreCase("1862695480")){
						saveMessage(s,"wei");
					}
					else{
						saveMessage(s);
					}
				}
			} catch (WeiboException e) {
				e.printStackTrace();
				Log.logInfo(e.getMessage());
			} 
			
		} 
		
	}
	
	private static void saveMessage(Status s){
		saveMessage(s,"db"+s.getUser().getId());
	
	}
	
	private static void saveMessage(Status s, String colName){
		String text = s.getText();
		if(text.contains("'")){
			text = text.replaceAll("'", " ");
			System.err.println("invalid json content from text in LoneSchicksal");
		}
		
		String originalText = "";
		if(null !=s.getRetweetedStatus()){
			originalText = s.getRetweetedStatus().getText();
			if(originalText.contains("'")){
				originalText = originalText.replaceAll("'", " ");
				System.err.println("invalid json content from original text in moda");
			}
		}
		String ss = "{strid"+":"+s.getIdstr()+",date"+":'"+sdf.format(s.getCreatedAt())+"', text: '"+text+"', originText: '"+(null ==s.getRetweetedStatus()? "":originalText)+"'}";
//		System.out.println(ss);
		WeiboDao.instance.save(ss,colName);
	}
	
	public static void main(String[] args) {
	
		ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
		pool.scheduleWithFixedDelay(new MyTask(), 0, 3L, TimeUnit.MINUTES);
		
//		new MyTask().run();
	}

}
