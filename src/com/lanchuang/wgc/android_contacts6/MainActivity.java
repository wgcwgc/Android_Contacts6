package com.lanchuang.wgc.android_contacts6;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity
{
	private static final String LOG = "LOG";

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fetchContactInformationV2();
	}

	public void fetchContactInformationV2()
	{
		String id;
		String mimetype;
		String str = null;
		ContentResolver contentResolver = this.getContentResolver();
		// 只需要从Contacts中获取ID，其他的都可以不要，通过查看上面编译后的SQL语句，可以看出将第二个参数
		// 设置成null，默认返回的列非常多，是一种资源浪费。
		Cursor cursor = contentResolver.query(android.provider.ContactsContract.Contacts.CONTENT_URI ,new String []
		{ android.provider.ContactsContract.Contacts._ID } ,null ,null ,null);
		while(cursor.moveToNext())
		{
			id = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID));

			// 从一个Cursor获取所有的信息
			Cursor contactInfoCursor = contentResolver.query(android.provider.ContactsContract.Data.CONTENT_URI ,new String []
			{ android.provider.ContactsContract.Data.CONTACT_ID, android.provider.ContactsContract.Data.MIMETYPE, android.provider.ContactsContract.Data.DATA1 } ,android.provider.ContactsContract.Data.CONTACT_ID + " = " + id ,null ,null);
			while(contactInfoCursor.moveToNext())
			{
				mimetype = contactInfoCursor.getString(contactInfoCursor.getColumnIndex(android.provider.ContactsContract.Data.MIMETYPE));
				String value = contactInfoCursor.getString(contactInfoCursor.getColumnIndex(android.provider.ContactsContract.Data.DATA1));
				if(mimetype.contains("/email"))
				{
					str = "邮箱 = " + value;
//					 new Print(str);
					Log.d(LOG , str);
				}
				else
					if(mimetype.contains(str + "/im"))
					{
						str = "聊天(QQ)账号 = " + value;
						// new Print(str);
						Log.d(LOG , str);
					}
					else
						if(mimetype.contains("/nickname"))
						{
							str = "昵称 = " + value;
							// new Print(str);
							Log.d(LOG , str);
						}
						else
							if(mimetype.contains("/organization"))
							{
								str = "工作单位 = " + value;
								// new Print(str);
								Log.d(LOG , str);
							}
							else
								if(mimetype.contains("/phone"))
								{
									str = "电话号码 = " + value;
									// new Print(str);
									Log.d(LOG , str);
								}
								else
									if(mimetype.contains("/sip_address"))
									{
										str = "sip地址 = " + value;
										// new Print(str);
										Log.d(LOG , str);
									}
									else
										if(mimetype.contains("/name"))
										{
											str = "姓名 = " + value;
											// new Print(str);
											Log.d(LOG , str);
										}
										else
											if(mimetype.contains("/postal"))
											{
												str = "住址 = " + value;
												// new Print(str);
												Log.d(LOG , str);
											}
											else
												if(mimetype.contains("/identity"))
												{
													str = "identity身份证 = " + value;
													// new Print(str);
													Log.d(LOG , str);
												}
												else
													if(mimetype.contains("/photo_default"))
													{
														str = "照片_default = " + value;
														// new Print(str);
														Log.d(LOG , str);
													}
													else
														if(mimetype.contains("/photo"))
														{
															str = "照片 = " + value;
															// new Print(str);
															Log.d(LOG , str);
														}
														else
															if(mimetype.contains("/group"))
															{
																str = "分组 = " + value;
																// new
																// Print(str);
																Log.d(LOG , str);
															}
															else
																if(mimetype.contains("/contact"))
																{
																	str = "出生日期 = " + value;
																	// new
																	// Print(str);
																	Log.d(LOG , str);
																}
																else
																	if(mimetype.contains("/note"))
																	{
																		str = "备注 = " + value;
																		// new
																		// Print(str);
																		Log.d(LOG , str);
																	}
																	else
																		if(mimetype.contains("/website"))
																		{
																			str = "网址 = " + value;
																			// new
																			// Print(str);
																			Log.d(LOG , str);
																		}
																		else
																			if(mimetype.contains("/relation"))
																			{
																				str = "亲属关系 = " + value;
																				// new
																				// Print(str);
																				Log.d(LOG , str);
																			}
																			else
																			{
																				str = "mimetype = " + mimetype + " value = " + value;
																				// new
																				// Print(str);
																				Log.d(LOG , str);
																			}
				/**
				 * /email_v2 /im /nickname /organization /sip_address /name
				 * /poatal-address_v2 /identity /photo /group_membership
				 * /contact_event /note /website /relation
				 * /vnd.com.tencent.mm.chatting .profile
				 * /vnd.com.tencent.mm.chatting .voip.video
				 * /vnd.com.tencent.mm.chatting .voiceaction
				 * /vnd.com.tencent.mm.plugin .sns.timeline
				 * vnd.com.tencent.mobileqq .voicecall.profile
				 */
			}
			str = "*********";
			Log.d(LOG , str);
			// new Print(str);
			contactInfoCursor.close();
		}
		cursor.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main ,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item )
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
