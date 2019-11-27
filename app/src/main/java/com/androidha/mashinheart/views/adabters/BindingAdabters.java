package com.androidha.mashinheart.views.adabters;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.models.ModelAppStore;
import com.androidha.mashinheart.views.application.MachinHeartApplication;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class BindingAdabters {


    //_________________________________ Start Car Adabter __________________________________________
    //______________________________________________________________________________________________
    @BindingAdapter(value = {"CarBrand"})
    public static void setImageBrand(CircleImageView view, int CarBrand) {
        view.setImageResource(view.getResources().obtainTypedArray(R.array.CarLogo).getResourceId(CarBrand, 0));
    }


    @BindingAdapter(value = {"CarBrand", "CarType", "CarColor"})
    public static void setNameCar(TextView view, int CarBrand, String CarType, String CarColor) {
        String brand = view.getContext().getResources().getStringArray(R.array.CarBrand)[CarBrand];
        StringBuilder sb = new StringBuilder();
        sb.append(brand);
        sb.append(" ");
        sb.append(CarType);
        sb.append(" ");
        sb.append(CarColor);
        view.setText(sb.toString());
    }


    @BindingAdapter(value = {"ChangeDate"})
    public static void setChandeDate(TextView view, Integer ChangeDate) {
        if (String.valueOf(ChangeDate).length() < 8) {
            view.setText(view.getContext().getResources().getString(R.string.NotSaveEnything));
            return;
        }
        String m = String.valueOf(ChangeDate).substring(4, 6);
        switch (Integer.valueOf(m).intValue()) {
            case 1:
                m = "فروردين";
                break;
            case 2:
                m = "ارديبهشت";
                break;
            case 3:
                m = "خرداد";
                break;
            case 4:
                m = "تير";
                break;
            case 5:
                m = "مرداد";
                break;
            case 6:
                m = "شهريور";
                break;
            case 7:
                m = "مهر";
                break;
            case 8:
                m = "آبان";
                break;
            case 9:
                m = "آذر";
                break;
            case 10:
                m = "دي";
                break;
            case 11:
                m = "بهمن";
                break;
            case 12:
                m = "اسفند";
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(ChangeDate).substring(6, 8));
        sb.append(" ");
        sb.append(m);
        sb.append(" ");
        sb.append(String.valueOf(ChangeDate).substring(0, 4));
        view.setText(sb.toString());
    }
    //___________________________________ End Car Adabter __________________________________________
    //______________________________________________________________________________________________


    //_________________________________ Start Consumable Adabter ___________________________________
    //______________________________________________________________________________________________
    @BindingAdapter(value = "TextConsumable")
    public static void setTextConsumable(TextView textView, String title) {
        String tag = textView.getTag().toString();
        if (title != null) {
            if (tag.equalsIgnoreCase("title")) {
                StringBuilder sb = new StringBuilder();
                sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeTitle));
                sb.append(" : ");
                sb.append(title);
                textView.setText(sb.toString());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeBrand));
                sb.append(" : ");
                sb.append(title);
                textView.setText(sb.toString());
            }
        } else {
            if (tag.equalsIgnoreCase("title")) {
                StringBuilder sb = new StringBuilder();
                sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeTitle));
                sb.append(" : ");
                sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                textView.setText(sb.toString());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeBrand));
                sb.append(" : ");
                sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                textView.setText(sb.toString());
            }
        }
    }

    @BindingAdapter(value = "ValueConsumable")
    public static void setValueConsumable(TextView textView, Integer value) {
        char c;
        TextView textView2 = textView;
        String tag = textView.getTag().toString();
        NumberFormat formatter = new DecimalFormat("#,###");
        if (value != null) {
            switch (tag) {
                case "changeKm":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeKM));
                    sb.append(" : ");
                    sb.append(formatter.format(value));
                    textView2.setText(sb.toString());
                    break;
                case "money":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb3.append(" : ");
                    sb3.append(formatter.format(value));
                    textView2.setText(sb3.toString());
                    break;
                case "changeNextKm":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeNextKM));
                    sb2.append(" : ");
                    sb2.append(formatter.format(value));
                    textView2.setText(sb2.toString());
                    break;
                case "changeDate":
                    String temp = String.valueOf(value);
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(temp.substring(0, 4));
                    sb4.append("/");
                    sb4.append(temp.substring(4, 6));
                    sb4.append("/");
                    sb4.append(temp.substring(6, 8));
                    String temp2 = sb4.toString();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(textView.getContext().getResources().getString(R.string.NewEventChangeDate));
                    sb5.append(" : ");
                    sb5.append(temp2);
                    textView2.setText(sb5.toString());
                    break;

            }
        } else {
            switch (tag) {
                case "changeKm":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeKM));
                    sb.append(" : ");
                    sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView2.setText(sb.toString());
                    break;
                case "money":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb3.append(" : ");
                    sb3.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView2.setText(sb3.toString());
                    break;
                case "changeNextKm":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeNextKM));
                    sb2.append(" : ");
                    sb2.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView2.setText(sb2.toString());
                    break;
                case "changeDate":
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(textView.getContext().getResources().getString(R.string.NewEventChangeDate));
                    sb5.append(" : ");
                    sb5.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView2.setText(sb5.toString());
                    break;
            }
        }
    }
    //___________________________________ End Consumable Adabter ___________________________________
    //______________________________________________________________________________________________


    //__________________________________ Start Insurance Adabter ___________________________________
    //______________________________________________________________________________________________
    @BindingAdapter(value = "TextInsurance")
    public static void setTextInsurance(TextView textView, String title) {
        if (title != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(textView.getContext().getResources().getString(R.string.InsuranceName));
            sb.append(" : ");
            sb.append(title);
            textView.setText(sb.toString());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(textView.getContext().getResources().getString(R.string.InsuranceName));
            sb.append(" : ");
            sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
            textView.setText(sb.toString());
        }
    }


    @BindingAdapter(value = "ValueInsurance")
    public static void setValueInsurance(TextView textView, Integer value) {
        String tag = textView.getTag().toString();
        NumberFormat formatter = new DecimalFormat("#,###");
        if (value != null) {
            switch (tag) {
                case "insuranceType":
                    String type = textView.getContext().getResources().getStringArray(R.array.InsuranceType)[value];
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.InsuranceType));
                    sb.append(" : ");
                    sb.append(type);
                    textView.setText(sb.toString());
                    break;
                case "insuranceDate":
                    String temp = String.valueOf(value);
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(temp.substring(0, 4));
                    sb1.append("/");
                    sb1.append(temp.substring(4, 6));
                    sb1.append("/");
                    sb1.append(temp.substring(6, 8));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.InsuranceDate));
                    sb2.append(" : ");
                    sb2.append(sb1.toString());
                    textView.setText(sb2.toString());
                    break;

                case "money":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb3.append(" : ");
                    sb3.append(formatter.format(value));
                    textView.setText(sb3.toString());
                    break;
            }
        } else {
            switch (tag) {
                case "insuranceType":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.InsuranceType));
                    sb.append(" : ");
                    sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb.toString());
                    break;
                case "insuranceDate":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.InsuranceDate));
                    sb2.append(" : ");
                    sb2.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb2.toString());
                    break;

                case "money":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb3.append(" : ");
                    sb3.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb3.toString());
                    break;
            }
        }
    }
    //____________________________________ End Insurance Adabter ___________________________________
    //______________________________________________________________________________________________


    //__________________________________ Start Repair Adabter ______________________________________
    //______________________________________________________________________________________________
    @BindingAdapter(value = "TextRepair")
    public static void setTextRepair(TextView textView, String title) {
        String tag = textView.getTag().toString();
        if (title != null) {
            switch (tag) {
                case "title":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventRepairTitle));
                    sb.append(" : ");
                    sb.append(title);
                    textView.setText(sb.toString());
                    break;

                case "brand":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeBrand));
                    sb2.append(" : ");
                    sb2.append(title);
                    textView.setText(sb2.toString());
                    break;

                case "repairWhy":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventRepairWhy));
                    sb3.append(" : ");
                    sb3.append(title);
                    textView.setText(sb3.toString());
                    break;
            }
        } else {
            switch (tag) {
                case "title":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventRepairTitle));
                    sb.append(" : ");
                    sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb.toString());
                    break;

                case "brand":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeBrand));
                    sb2.append(" : ");
                    sb2.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb2.toString());
                    break;

                case "repairWhy":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventRepairWhy));
                    sb3.append(" : ");
                    sb3.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb3.toString());
                    break;
            }
        }
    }

    @BindingAdapter(value = "ValueRepair")
    public static void setValueRepair(TextView textView, Integer value) {
        String tag = textView.getTag().toString();
        NumberFormat formatter = new DecimalFormat("#,###");
        if (value != null) {
            switch (tag) {
                case "repairDate":
                    String temp = String.valueOf(value);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(temp.substring(0, 4));
                    sb3.append("/");
                    sb3.append(temp.substring(4, 6));
                    sb3.append("/");
                    sb3.append(temp.substring(6, 8));
                    String temp2 = sb3.toString();
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(textView.getContext().getResources().getString(R.string.NewEventRepairDate));
                    sb4.append(" : ");
                    sb4.append(temp2);
                    textView.setText(sb4.toString());
                    break;
                case "money":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb2.append(" : ");
                    sb2.append(formatter.format(value));
                    textView.setText(sb2.toString());
                    break;

                case "repairKm":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventRepairKM));
                    sb.append(" : ");
                    sb.append(formatter.format(value));
                    textView.setText(sb.toString());
                    break;
            }
        } else {
            switch (tag) {
                case "repairDate":
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(textView.getContext().getResources().getString(R.string.NewEventRepairDate));
                    sb4.append(" : ");
                    sb4.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb4.toString());
                    break;
                case "money":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb2.append(" : ");
                    sb2.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb2.toString());
                    break;

                case "repairKm":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventRepairKM));
                    sb.append(" : ");
                    sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb.toString());
                    break;
            }
        }
    }
    //____________________________________ End Repair Adabter ______________________________________
    //______________________________________________________________________________________________


    //__________________________________ Start Advertise Adabter ___________________________________
    //______________________________________________________________________________________________
    @BindingAdapter(value = {"AdvertiseAddress", "StoreAddress"})
    public static void SetAdvertiseText(TextView textView, String AdvertiseAddress, ModelAppStore StoreAddress) {
        if (AdvertiseAddress != null) {
            String tag = textView.getTag().toString();
            if (tag.equalsIgnoreCase("address"))
                textView.setText(textView.getContext().getResources().getString(R.string.Address) + " : " + AdvertiseAddress);
            else
                textView.setText(textView.getContext().getResources().getString(R.string.Tel) + " : " + AdvertiseAddress);
        } else {
            String tag = textView.getTag().toString();
            if (tag.equalsIgnoreCase("address"))
                textView.setText(textView.getContext().getResources().getString(R.string.Address) + " : " + StoreAddress.getAddress());
            else if (tag.equalsIgnoreCase("tel")) {
                textView.setText(textView.getContext().getResources().getString(R.string.Tel) + " : " + StoreAddress.getMobileNumber());
            } else {
                textView.setVisibility(View.VISIBLE);
                if (StoreAddress != null)
                    textView.setText(textView.getContext().getResources().getString(R.string.Store) + " : " + StoreAddress.getNameStore());
                else
                    textView.setVisibility(View.GONE);
            }
        }
    }


    @BindingAdapter(value = {"Store"})
    public static void SetImageAgaring(ImageView imageView, Boolean IsAdminAdvertise) {
        if (!IsAdminAdvertise)
            imageView.setImageResource(R.drawable.ic_done_all_black_24dp);
        else
            imageView.setImageResource(R.drawable.ic_do_not_disturb_alt_black_24dp);

    }


    @BindingAdapter(value = {"Image"})
    public static void SetImageAdvertise(ImageView imageView, String Url) {
        if ((Url != null) && (!Url.equalsIgnoreCase(""))) {
            Url = imageView.getContext().getResources().getString(R.string.AgaringLink) + Url;
            ImageLoader imageLoader = MachinHeartApplication
                    .getMachinHeartApplication(imageView.getContext())
                    .getImageLoaderComponent()
                    .getImageLoader();

            imageLoader.displayImage(Url, imageView, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    //holder.spin_kit.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    //holder.spin_kit.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    //holder.spin_kit.setVisibility(View.GONE);
                }
            });
        }
    }
    //____________________________________ End Advertise Adabter ___________________________________
    //______________________________________________________________________________________________


    //__________________________________ Start PoliceFine Adabter __________________________________
    //______________________________________________________________________________________________
    @BindingAdapter(value = "IntFine")
    public static void SetIntFine(TextView textView, Integer price) {
        if (price != null) {
            NumberFormat formatter = new DecimalFormat("#,###");
            StringBuilder sb = new StringBuilder();
            sb.append(textView.getContext().getResources().getString(R.string.InfringementPrice));
            sb.append(" : ");
            sb.append(formatter.format(price));
            sb.append(" ");
            sb.append(textView.getContext().getResources().getString(R.string.Rial));
            textView.setText(sb.toString());
        } else {
            NumberFormat formatter = new DecimalFormat("#,###");
            StringBuilder sb = new StringBuilder();
            sb.append(textView.getContext().getResources().getString(R.string.InfringementPrice));
            sb.append(" : ");
            sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
            textView.setText(sb.toString());
        }
    }

    @BindingAdapter(value = "TextFine")
    public static void setTextFine(TextView textView, String Value) {
        String tag = textView.getTag().toString();
        if (Value != null) {

            switch (tag) {
                case "serial":
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(textView.getContext().getResources().getString(R.string.InfringementSerial));
                    sb7.append(" : ");
                    sb7.append(Value);
                    textView.setText(sb7.toString());
                    break;

                case "pay":
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(textView.getContext().getResources().getString(R.string.InfringementPay));
                    sb8.append(" : ");
                    sb8.append(Value);
                    textView.setText(sb8.toString());
                    break;

                case "bill":
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append(textView.getContext().getResources().getString(R.string.InfringementBill));
                    sb9.append(" : ");
                    sb9.append(Value);
                    textView.setText(sb9.toString());
                    break;

                case "city":
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(textView.getContext().getResources().getString(R.string.InfringementCity));
                    sb6.append(" : ");
                    sb6.append(Value);
                    textView.setText(sb6.toString());
                    break;

                case "code":
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(textView.getContext().getResources().getString(R.string.InfringementCode));
                    sb4.append(" : ");
                    sb4.append(Value);
                    textView.setText(sb4.toString());
                    break;

                case "date":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.InfringementDate));
                    sb.append(" : ");
                    sb.append(Value);
                    textView.setText(sb.toString());
                    break;

                case "type":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.InfringementType));
                    sb2.append(" : ");
                    sb2.append(Value);
                    textView.setText(sb2.toString());
                    break;

                case "description":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.InfringementDiscription));
                    sb3.append(" : ");
                    sb3.append(Value);
                    textView.setText(sb3.toString());
                    break;

                case "location":
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(textView.getContext().getResources().getString(R.string.InfringementLocation));
                    sb5.append(" : ");
                    sb5.append(Value);
                    textView.setText(sb5.toString());
                    break;
            }
        } else {
            switch (tag) {
                case "serial":
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(textView.getContext().getResources().getString(R.string.InfringementSerial));
                    sb7.append(" : ");
                    sb7.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb7.toString());
                    break;

                case "pay":
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(textView.getContext().getResources().getString(R.string.InfringementPay));
                    sb8.append(" : ");
                    sb8.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb8.toString());
                    break;

                case "bill":
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append(textView.getContext().getResources().getString(R.string.InfringementBill));
                    sb9.append(" : ");
                    sb9.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb9.toString());
                    break;

                case "city":
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(textView.getContext().getResources().getString(R.string.InfringementCity));
                    sb6.append(" : ");
                    sb6.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb6.toString());
                    break;

                case "code":
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(textView.getContext().getResources().getString(R.string.InfringementCode));
                    sb4.append(" : ");
                    sb4.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb4.toString());
                    break;

                case "date":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.InfringementDate));
                    sb.append(" : ");
                    sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb.toString());
                    break;

                case "type":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.InfringementType));
                    sb2.append(" : ");
                    sb2.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb2.toString());
                    break;

                case "description":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.InfringementDiscription));
                    sb3.append(" : ");
                    sb3.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb3.toString());
                    break;

                case "location":
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(textView.getContext().getResources().getString(R.string.InfringementLocation));
                    sb5.append(" : ");
                    sb5.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb5.toString());
                    break;
            }
        }

    }
    //____________________________________ End PoliceFine Adabter __________________________________
    //______________________________________________________________________________________________


}
