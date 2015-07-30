package weibo4j.examples.timeline;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
public class GetHomeTimeline {

	static class MyTask implements Runnable{
		static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		static File output = new File("./weibo.er.txt");
		@Override
		public void run() {
			//通过API测试工具获取
			String access_token = "2.00ZTQAvCOrgHnD16b836f56bX5c2ME";
			access_token = "2.00ZTQAvCFkfoyC9a8070103dvoYVBE";
			Timeline tm = new Timeline();
			tm.client.setToken(access_token);
			System.out.println("running at : "+sdf.format(new Date()));
			try {
				StatusWapper status = tm.getHomeTimeline();
				
				for(Status s : status.getStatuses()){
					
					if(s.toString().contains("1767797335")){
//						Log.logInfo(s.toString());
						String text = s.getText();
						if(text.contains("'")){
							text = text.replaceAll("'", " ");
							System.err.println("invalid json content from text in ER");
						}
						
						String originalText = "";
						if(null !=s.getRetweetedStatus()){
							originalText = s.getRetweetedStatus().getText();
							if(originalText.contains("'")){
								originalText = originalText.replaceAll("'", " ");
								System.err.println("invalid json content from original text in ER");
							}
						}
						
						
						String ss = "{strid"+":"+s.getIdstr()+",date"+":'"+sdf.format(s.getCreatedAt())+"', text: '"+text+"', originText: '"+(null ==s.getRetweetedStatus()? "":originalText)+"'}";
//						System.out.println(ss);
						WeiboDao.instance.save(ss,"ER");
					}else if(s.toString().contains("1752340457")){
						
						String text = s.getText();
						if(text.contains("'")){
							text = text.replaceAll("'", " ");
							System.err.println("invalid json content from text in Mechanic");
						}
						
						String originalText = "";
						if(null !=s.getRetweetedStatus()){
							originalText = s.getRetweetedStatus().getText();
							if(originalText.contains("'")){
								originalText = originalText.replaceAll("'", " ");
								System.err.println("invalid json content from original text in Mechanic");
							}
						}
						String ss = "{strid"+":"+s.getIdstr()+",date"+":'"+sdf.format(s.getCreatedAt())+"', text: '"+text+"', originText: '"+(null ==s.getRetweetedStatus()? "":originalText)+"'}";
//						System.out.println(ss);
						WeiboDao.instance.save(ss,"Mechanic");
						
					}else if(s.toString().contains("2074611540")){
						
						String text = s.getText();
						if(text.contains("'")){
							text = text.replaceAll("'", " ");
							System.err.println("invalid json content from text in Mechanic");
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
//						System.out.println(ss);
						WeiboDao.instance.save(ss,"moda");
						
					}else if(s.getUser().getId().equalsIgnoreCase("1645776681")){//LoneSchicksal
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
//						System.out.println(ss);
						WeiboDao.instance.save(ss,"Lone");
						
					
					}else if(s.getUser().getId().equalsIgnoreCase("1769173661")){//peng
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
//						System.out.println(ss);
						WeiboDao.instance.save(ss,"Peng");
						
					
					}
					else{
						System.out.println("someone else's weibo");
					}
				}
			} catch (WeiboException e) {
				e.printStackTrace();
				Log.logInfo(e.getMessage());
			} 
			
		} 
		
	}
	public static void main(String[] args) {
	
		ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
		pool.scheduleWithFixedDelay(new MyTask(), 0, 4L, TimeUnit.MINUTES);
		
//		new MyTask().run();
	}

}
