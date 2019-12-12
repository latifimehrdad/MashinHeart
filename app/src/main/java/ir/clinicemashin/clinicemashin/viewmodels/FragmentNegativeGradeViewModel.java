package ir.clinicemashin.clinicemashin.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;

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

public class FragmentNegativeGradeViewModel {

    private Bitmap CaptchaBitmap;
    public PublishSubject<String> MessageType = PublishSubject.create();
    private String captId;
    public Context context;
    private String cookie;
    private String html;
    private String key;
    private String stsp;


    public FragmentNegativeGradeViewModel(Context context2) {//_____________________________________ Start FragmentNegativeGradeViewModel
        this.context = context2;
    }//_____________________________________________________________________________________________ End FragmentNegativeGradeViewModel


    public void GetImageCaptcha(final Boolean Error, final Boolean Two) {//_________________________ Start GetImageCaptcha

        Volley
                .newRequestQueue(context)
                .add(new StringRequest(0, "http://estelam.rahvar120.ir/?siteid=1&pageid=395", new Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);
                            String[] k = document.select("img[id=ch_capt]").attr("onclick").split("key=");
                            FragmentNegativeGradeViewModel.this.key = k[1].split("&rand")[0];
                            FragmentNegativeGradeViewModel.this.captId = document.select("input[id=cptchid]").attr("value");
                            FragmentNegativeGradeViewModel.this.stsp = k[0].split("stsp=")[1];
                            FragmentNegativeGradeViewModel.this.stsp = FragmentNegativeGradeViewModel.this.stsp.substring(0, FragmentNegativeGradeViewModel.this.stsp.length() - 1);
                            if (Two) {
                                FragmentNegativeGradeViewModel.this.getImage(Error);
                            } else {
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        FragmentNegativeGradeViewModel.this.GetImageCaptchaTwo(Error);
                                    }
                                }, 5000);
                            }
                        } catch (Exception e) {
                            FragmentNegativeGradeViewModel.this.MessageType.onNext(FragmentNegativeGradeViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
                        }
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        FragmentNegativeGradeViewModel.this.MessageType.onNext(FragmentNegativeGradeViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
                    }
                }) {
                    public Response<String> parseNetworkResponse(NetworkResponse response) {
                        FragmentNegativeGradeViewModel.this.cookie = (String) response.headers.get(Parameter.gradeH4);
                        return super.parseNetworkResponse(response);
                    }
                });
    }//_____________________________________________________________________________________________ End GetImageCaptcha


    public void GetImageCaptchaTwo(final Boolean Error) {//_________________________________________ Start GetImageCaptchaTwo

        Volley
                .newRequestQueue(context)
                .add(new StringRequest(0, "http://estelam.rahvar120.ir/?siteid=1&pageid=395", new Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);
                            String[] k = document.select("img[id=ch_capt]").attr("onclick").split("key=");
                            FragmentNegativeGradeViewModel.this.key = k[1].split("&rand")[0];
                            FragmentNegativeGradeViewModel.this.captId = document.select("input[id=cptchid]").attr("value");
                            FragmentNegativeGradeViewModel.this.stsp = k[0].split("stsp=")[1];
                            FragmentNegativeGradeViewModel.this.stsp = FragmentNegativeGradeViewModel.this.stsp.substring(0, FragmentNegativeGradeViewModel.this.stsp.length() - 1);
                            FragmentNegativeGradeViewModel.this.getImage(Error);
                        } catch (Exception e) {
                            FragmentNegativeGradeViewModel.this.MessageType.onNext(FragmentNegativeGradeViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
                        }
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        FragmentNegativeGradeViewModel.this.MessageType.onNext(FragmentNegativeGradeViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
                    }
                }) {
                    public Response<String> parseNetworkResponse(NetworkResponse response) {
                        FragmentNegativeGradeViewModel.this.cookie = (String) response.headers.get(Parameter.gradeH4);
                        return super.parseNetworkResponse(response);
                    }
                });
    }//_____________________________________________________________________________________________ End GetImageCaptchaTwo


    private void getImage(final Boolean Error) {//__________________________________________________ Start getImage
        StringBuilder sb = new StringBuilder();
        String str = "[key]";
        sb.append(Parameter.captUrl.replace("[stsp]", this.stsp).replace(str, this.key));
        sb.append(Math.random());
        String url = sb.toString();
        Volley.newRequestQueue(context).add(new ImageRequest(url, new Listener<Bitmap>() {
            public void onResponse(Bitmap bitmap) {
                String m;
                FragmentNegativeGradeViewModel.this.CaptchaBitmap = bitmap;
                if (!Error) {
                    m = FragmentNegativeGradeViewModel.this.context.getResources().getString(R.string.GetCaptchaOk);
                } else {
                    m = FragmentNegativeGradeViewModel.this.context.getResources().getString(R.string.GetCaptchaError);
                }
                FragmentNegativeGradeViewModel.this.MessageType.onNext(m);
            }
        }, 0, 0, null, Config.RGB_565, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FragmentNegativeGradeViewModel.this.MessageType.onNext(FragmentNegativeGradeViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
            }
        }) {
            public Response<Bitmap> parseNetworkResponse(NetworkResponse response) {
                FragmentNegativeGradeViewModel.this.cookie = (String) response.headers.get(Parameter.gradeH4);
                return super.parseNetworkResponse(response);
            }

            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put(Parameter.captP1t, Parameter.captP1v);
                params.put(Parameter.captP2t, FragmentNegativeGradeViewModel.this.cookie);
                return params;
            }
        });
    }//_____________________________________________________________________________________________ End getImage


    public void CheckCaptcha(String Barcode, String Captcha) {//____________________________________ Start CheckCaptcha
        String url = Parameter.gradeUrl;
        Volley.newRequestQueue(context).add(new StringRequest(1, url, new Listener<String>() {
            public void onResponse(String response) {
                FragmentNegativeGradeViewModel.this.checkHtml(response);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FragmentNegativeGradeViewModel.this.MessageType.onNext(FragmentNegativeGradeViewModel.this.context.getResources().getString(R.string.ErrorConnectToPolice));
            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put(Parameter.gradeH1, Parameter.gradeH1V);
                params.put(Parameter.gradeH2, Parameter.gradeH2V);
                params.put(Parameter.gradeH3, FragmentNegativeGradeViewModel.this.cookie);
                return params;
            }

            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Parameter.gradeT1, Parameter.gradeV1);
                params.put(Parameter.gradeT2, Parameter.gradeV2);
                params.put(Parameter.gradeT3, Parameter.gradeV3);
                params.put(Parameter.gradeT4, Barcode);
                params.put(Parameter.gradeT5, Captcha);
                params.put(Parameter.gradeH5, Parameter.gradeH5V);
                return params;
            }

            public Response<String> parseNetworkResponse(NetworkResponse response) {
                Map<String, String> map = response.headers;
                return super.parseNetworkResponse(response);
            }
        });
    }//_____________________________________________________________________________________________ End CheckCaptcha


    private void checkHtml(String html2) {//________________________________________________________ Start checkHtml
        if (html2.contains("access deny")) {
            this.MessageType.onNext(this.context.getResources().getString(R.string.ErrorConnectToPolice));
        } else if (html2.contains("متن درون تصویر اشتباه وارد شده است")) {
            GetImageCaptcha(Boolean.valueOf(true), Boolean.valueOf(false));
        } else if (html2.contains("ispreview")) {
            setHtml(html2);
            this.MessageType.onNext(this.context.getResources().getString(R.string.PoliceFineOk));
        }
    }//_____________________________________________________________________________________________ End checkHtml


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
}
