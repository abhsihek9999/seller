package com.hk.seller.utils.alert_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.hk.seller.R;
import com.hk.seller.databinding.DialogErrorCustomBinding;

import androidx.databinding.DataBindingUtil;


public class CommonErrorDialog extends Dialog implements View.OnClickListener {

    public Context context;
    public Dialog dialog;
    private String msg;
    private String singleBtnTxt;
    private String tittle;
    OkCallcack okCallcack;
    SecondCallcack secondCallcack;
    FirstCallcack firstCallcack;
    private DialogErrorCustomBinding binding;


    public CommonErrorDialog(Context a, String tittle, String msg) {

        super(a);
        this.context = a;
        this.msg = msg;
        this.tittle = tittle;
    }

    public void setOkCallback(OkCallcack okCallcack) {
        this.okCallcack = okCallcack;
    }

    public void setFirstCallcack(FirstCallcack firstCallcack) {
        this.firstCallcack = firstCallcack;
    }

    public void setSecondCallcack(SecondCallcack secondCallcack) {
        this.secondCallcack = secondCallcack;
    }

    public void setSingleBtnTxt(String singleBtnTxt) {
        this.singleBtnTxt = singleBtnTxt;
    }


    public void showProgress(boolean isShow, String btnTxt) {

        if (isShow) {
            binding.progress.setVisibility(View.VISIBLE);
            binding.btnYes.setText("");
            binding.btnYes.setClickable(false);

        } else {
            binding.progress.setVisibility(View.GONE);
            binding.btnYes.setText(btnTxt);
            binding.btnYes.setClickable(true);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_error_custom, null, false);
        setContentView(binding.getRoot());

        binding.tvTitle.setText(tittle);
        binding.tvMessage.setText(msg);
       /* if (mobileTxt == null) {
            binding.numConfirmationText.setVisibility(View.GONE);
        } else {
            binding.numConfirmationText.setVisibility(View.VISIBLE);
            binding.numConfirmationText.setText(mobileTxt);
        }*/
        binding.numConfirmationText.setVisibility(View.GONE);
        binding.btnYes.setVisibility(View.VISIBLE);
        binding.btnNo.setVisibility(View.GONE);

        binding.btnYes.setText(singleBtnTxt);

        binding.btnYes.setOnClickListener(this);

       /* if (istwoBtnRequired) {
            binding.btnYes.setVisibility(View.VISIBLE);
            binding.btnNo.setVisibility(View.VISIBLE);


            binding.btnYes.setText(secondBtnTxt);
            binding.btnNo.setText(firstBtnTxt);

            binding.btnYes.setOnClickListener(this);
            binding.btnNo.setOnClickListener(this);
        } else {
            binding.btnYes.setVisibility(View.VISIBLE);
            binding.btnNo.setVisibility(View.GONE);

            binding.btnYes.setText(singleBtnTxt);

            binding.btnYes.setOnClickListener(this);
        }
*/

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_no:
                // dismiss();
                firstCallcack.clickNoCallback();
                break;

            case R.id.btn_yes:
                // dismiss();

              /*  if (istwoBtnRequired) {
                    secondCallcack.clickYesCallback();
                } else {
                    okCallcack.clickOkCallback();
                }*/
                okCallcack.clickOkCallback();

                break;

            default:
                break;
        }
    }

    public interface OkCallcack {
        void clickOkCallback();
    }

    public interface SecondCallcack {
        void clickYesCallback();
    }

    public interface FirstCallcack {
        void clickNoCallback();
    }
}
