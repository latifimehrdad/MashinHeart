package ir.clinicemashin.clinicemashin.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.utility.Parameter;

import io.reactivex.subjects.PublishSubject;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FragmentPoliceFineViewModel {

    private Bitmap CaptchaBitmap;
    private PublishSubject<String> MessageType = PublishSubject.create();
    private String captId;
    private Context context;
    private String cookie;
    private String duration;
    private String html;
    private String key;
    private String rc;
    private String stsp;


    public FragmentPoliceFineViewModel(Context context2) {//________________________________________ Start FragmentPoliceFineViewModel
        this.context = context2;
    }//_____________________________________________________________________________________________ End FragmentPoliceFineViewModel


    public void GetImageCaptcha(final Boolean Error) {//____________________________________________ Start GetImageCaptcha

        Volley
                .newRequestQueue(context)
                .add(new StringRequest(0, "http://estelam.rahvar120.ir/index.jsp?siteid=1&fkeyid=&siteid=1&pageid=2371666", new Listener<String>() {
                    public void onResponse(String response) {
                        String str = "value";
                        try {
                            Document document = Jsoup.parse(response);
                            String[] k = document.select("img[id=ch_capt]").attr("onclick").split("key=");
                            FragmentPoliceFineViewModel.this.stsp = k[0].split("stsp=")[1];
                            FragmentPoliceFineViewModel.this.stsp = FragmentPoliceFineViewModel.this.stsp.substring(0, FragmentPoliceFineViewModel.this.stsp.length() - 1);
                            FragmentPoliceFineViewModel.this.key = k[1].split("&rand")[0];
                            FragmentPoliceFineViewModel.this.captId = document.select("input[id=cptchid]").attr(str);
                            FragmentPoliceFineViewModel.this.rc = document.select("input[name=rc]").attr(str);
                            FragmentPoliceFineViewModel.this.duration = document.select("input[id=duration]").attr(str);
                            FragmentPoliceFineViewModel.this.getImage(Error);
                        } catch (Exception e) {
                            FragmentPoliceFineViewModel.this.MessageType.onNext(FragmentPoliceFineViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
                        }
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        FragmentPoliceFineViewModel.this.MessageType.onNext(FragmentPoliceFineViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
                    }
                }) {
                    public Response<String> parseNetworkResponse(NetworkResponse response) {
                        FragmentPoliceFineViewModel.this.cookie = response.headers.get(Parameter.gradeH4);
                        return super.parseNetworkResponse(response);
                    }
                });
    }//_____________________________________________________________________________________________ End GetImageCaptcha


    private void getImage(final Boolean Error) {//__________________________________________________ Start getImage
        StringBuilder sb = new StringBuilder();
        String str = "[key]";
        sb.append("http://estelam.rahvar120.ir/includes/captcha.jpg?stsp=[stsp]&key=[key]&rand="
                .replace("[stsp]", this.stsp)
                .replace(str, this.key));
        sb.append(Math.random());
        String url = sb.toString();
        Volley.newRequestQueue(context).add(new ImageRequest(url, new Listener<Bitmap>() {
            public void onResponse(Bitmap bitmap) {
                String m;
                FragmentPoliceFineViewModel.this.CaptchaBitmap = bitmap;
                if (!Error) {
                    m = FragmentPoliceFineViewModel.this.context.getResources().getString(R.string.GetCaptchaOk);
                } else {
                    m = FragmentPoliceFineViewModel.this.context.getResources().getString(R.string.GetCaptchaError);
                }
                FragmentPoliceFineViewModel.this.MessageType.onNext(m);
            }
        }, 0, 0, null, Config.RGB_565, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FragmentPoliceFineViewModel.this.MessageType.onNext(FragmentPoliceFineViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
            }
        }) {
            public Response<Bitmap> parseNetworkResponse(NetworkResponse response) {
                FragmentPoliceFineViewModel.this.cookie = (String) response.headers.get(Parameter.gradeH4);
                return super.parseNetworkResponse(response);
            }

            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put(Parameter.captP1t, Parameter.captP1v);
                params.put(Parameter.captP2t, FragmentPoliceFineViewModel.this.cookie);
                return params;
            }
        });
    }//_____________________________________________________________________________________________ End getImage


    public void CheckCaptcha(final String Barcode, final String Captcha) {//________________________ Start CheckCaptcha

        Volley
                .newRequestQueue(context)
                .add(new StringRequest(1, "http://estelam.rahvar120.ir/index.jsp?siteid=1&fkeyid=&siteid=1&pageid=2371666", new Listener<String>() {
                    public void onResponse(String response) {
                        if (FragmentPoliceFineViewModel.this.checkHtml(response)) {
                            FragmentPoliceFineViewModel.this.GetPoliceFine(Barcode, Captcha);
                        }
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        FragmentPoliceFineViewModel.this.MessageType.onNext(FragmentPoliceFineViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
                    }
                }) {
                    public Map<String, String> getHeaders() {
                        Map<String, String> params = new HashMap<>();
                        params.put(Parameter.OffenseH1, "http://estelam.rahvar120.ir/index.jsp?siteid=1&fkeyid=&siteid=1&pageid=2371666");
                        params.put(Parameter.OffenseH2, Parameter.OffenseHv2);
                        params.put(Parameter.gradeH3, FragmentPoliceFineViewModel.this.cookie);
                        return params;
                    }

                    public Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("aform", "add");
                        params.put("rc", FragmentPoliceFineViewModel.this.rc);
                        params.put("duration", FragmentPoliceFineViewModel.this.duration);
                        params.put("hashtraghami", Barcode);
                        params.put("cptchid", FragmentPoliceFineViewModel.this.captId);
                        params.put("capcha", Captcha);
                        String str = "submit";
                        params.put(str, str);
                        String str2 = "";
                        params.put("cr", str2);
                        params.put("refno", str2);
                        return params;
                    }

                    public Response<String> parseNetworkResponse(NetworkResponse response) {
                        FragmentPoliceFineViewModel.this.cookie = response.headers.get(Parameter.gradeH4);
                        return super.parseNetworkResponse(response);
                    }
                });
    }//_____________________________________________________________________________________________ End CheckCaptcha


    private boolean checkHtml(String html2) {//_____________________________________________________ Start checkHtml
        if (!html2.contains("متن درون تصویر اشتباه وارد شده است")) {
            return !html2.contains("access deny");
        }
        GetImageCaptcha(true);
        return false;
    }//_____________________________________________________________________________________________ End checkHtml


    private void GetPoliceFine(String Barcode, String Captcha) {//__________________________________ Start GetPoliceFine
        String url = Parameter.OffenseUrl;
        Volley.newRequestQueue(context).add(new StringRequest(1, url, new Listener<String>() {
            public void onResponse(String response) {
                FragmentPoliceFineViewModel.this.setHtml(response);
                FragmentPoliceFineViewModel.this.MessageType.onNext(FragmentPoliceFineViewModel.this.context.getResources().getString(R.string.PoliceFineOk));
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FragmentPoliceFineViewModel.this.MessageType.onNext(FragmentPoliceFineViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                String str = Parameter.OffenseH1;
                StringBuilder sb = new StringBuilder();
                sb.append(Parameter.OffenseHv1);
                sb.append(str);
                params.put(str, sb.toString());
                params.put(Parameter.OffenseH2, Parameter.OffenseHv2);
                params.put(Parameter.gradeH3, FragmentPoliceFineViewModel.this.cookie);
                return params;
            }

            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Parameter.Offenset1, Barcode);
                params.put(Parameter.Offenset2, Captcha);
                params.put("cptchid", FragmentPoliceFineViewModel.this.captId);
                return params;
            }
        });
    }//_____________________________________________________________________________________________ End GetPoliceFine


    public Bitmap getCaptchaBitmap() {
        return this.CaptchaBitmap;
    }

    public void setCaptchaBitmap(Bitmap captchaBitmap) {
        this.CaptchaBitmap = captchaBitmap;
    }

    public String getHtml() {
        return this.html;
    }

    public void setHtml(String html2) {
        this.html = html2;
    }

    public PublishSubject<String> getMessageType() {
        return MessageType;
    }
}
