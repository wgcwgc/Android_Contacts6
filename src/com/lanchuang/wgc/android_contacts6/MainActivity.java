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
		// ֻ��Ҫ��Contacts�л�ȡID�������Ķ����Բ�Ҫ��ͨ���鿴���������SQL��䣬���Կ������ڶ�������
		// ���ó�null��Ĭ�Ϸ��ص��зǳ��࣬��һ����Դ�˷ѡ�
		Cursor cursor = contentResolver.query(android.provider.ContactsContract.Contacts.CONTENT_URI ,new String []
		{ android.provider.ContactsContract.Contacts._ID } ,null ,null ,null);
		while(cursor.moveToNext())
		{
			id = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID));

			// ��һ��Cursor��ȡ���е���Ϣ
			Cursor contactInfoCursor = contentResolver.query(android.provider.ContactsContract.Data.CONTENT_URI ,new String []
			{ android.provider.ContactsContract.Data.CONTACT_ID, android.provider.ContactsContract.Data.MIMETYPE, android.provider.ContactsContract.Data.DATA1 } ,android.provider.ContactsContract.Data.CONTACT_ID + " = " + id ,null ,null);
			while(contactInfoCursor.moveToNext())
			{
				mimetype = contactInfoCursor.getString(contactInfoCursor.getColumnIndex(android.provider.ContactsContract.Data.MIMETYPE));
				String value = contactInfoCursor.getString(contactInfoCursor.getColumnIndex(android.provider.ContactsContract.Data.DATA1));
				if(mimetype.contains("/email"))
				{
					str = "���� = " + value;
//					 new Print(str);
					Log.d(LOG , str);
				}
				else
					if(mimetype.contains(str + "/im"))
					{
						str = "����(QQ)�˺� = " + value;
						// new Print(str);
						Log.d(LOG , str);
					}
					else
						if(mimetype.contains("/nickname"))
						{
							str = "�ǳ� = " + value;
							// new Print(str);
							Log.d(LOG , str);
						}
						else
							if(mimetype.contains("/organization"))
							{
								str = "������λ = " + value;
								// new Print(str);
								Log.d(LOG , str);
							}
							else
								if(mimetype.contains("/phone"))
								{
									str = "�绰���� = " + value;
									// new Print(str);
									Log.d(LOG , str);
								}
								else
									if(mimetype.contains("/sip_address"))
									{
										str = "sip��ַ = " + value;
										// new Print(str);
										Log.d(LOG , str);
									}
									else
										if(mimetype.contains("/name"))
										{
											str = "���� = " + value;
											// new Print(str);
											Log.d(LOG , str);
										}
										else
											if(mimetype.contains("/postal"))
											{
												str = "סַ = " + value;
												// new Print(str);
												Log.d(LOG , str);
											}
											else
												if(mimetype.contains("/identity"))
												{
													str = "identity���֤ = " + value;
													// new Print(str);
													Log.d(LOG , str);
												}
												else
													if(mimetype.contains("/photo_default"))
													{
														str = "��Ƭ_default = " + value;
														// new Print(str);
														Log.d(LOG , str);
													}
													else
														if(mimetype.contains("/photo"))
														{
															str = "��Ƭ = " + value;
															// new Print(str);
															Log.d(LOG , str);
														}
														else
															if(mimetype.contains("/group"))
															{
																str = "���� = " + value;
																// new
																// Print(str);
																Log.d(LOG , str);
															}
															else
																if(mimetype.contains("/contact"))
																{
																	str = "�������� = " + value;
																	// new
																	// Print(str);
																	Log.d(LOG , str);
																}
																else
																	if(mimetype.contains("/note"))
																	{
																		str = "��ע = " + value;
																		// new
																		// Print(str);
																		Log.d(LOG , str);
																	}
																	else
																		if(mimetype.contains("/website"))
																		{
																			str = "��ַ = " + value;
																			// new
																			// Print(str);
																			Log.d(LOG , str);
																		}
																		else
																			if(mimetype.contains("/relation"))
																			{
																				str = "������ϵ = " + value;
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
