package info.androidhive.bottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.btn_bottom_sheet)
    Button btnBottomSheet;

    @BindView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;

    @BindView(R.id.bottom_sheet_collapsed)
    LinearLayout layoutBottomSheetCollapsed;

    @BindView(R.id.closeBtn)
    ImageView closeButton;

    BottomSheetBehavior sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        //layoutBottomSheetCollapsed.setVisibility(View.GONE);
                        btnBottomSheet.setText("Close Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        layoutBottomSheetCollapsed.setVisibility(View.VISIBLE);
                        btnBottomSheet.setText("Expand Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        //layoutBottomSheetCollapsed.setVisibility(View.GONE);
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        //layoutBottomSheetCollapsed.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d("Hello", "Current Slide : " + slideOffset);
                layoutBottomSheetCollapsed.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.closeBtn)
    public void showCollapseBottomSheet(){
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    /**
     * manually opening / closing bottom sheet on button click
     */
    @OnClick(R.id.btn_bottom_sheet)
    public void toggleBottomSheet() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            btnBottomSheet.setText("Close sheet");
        }

        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            btnBottomSheet.setText("Expand sheet");
        }

        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            btnBottomSheet.setText("Close sheet");
        }
    }

//    /**
//     * showing bottom sheet dialog
//     */
//    @OnClick(R.id.btn_bottom_sheet_dialog)
//    public void showBottomSheetDialog() {
//        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);
//
//        BottomSheetDialog dialog = new BottomSheetDialog(this);
//        dialog.setContentView(view);
//        dialog.show();
//    }
//
//
//    /**
//     * showing bottom sheet dialog fragment
//     * same layout is used in both dialog and dialog fragment
//     */
//    @OnClick(R.id.btn_bottom_sheet_dialog_fragment)
//    public void showBottomSheetDialogFragment() {
//        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
//        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
//    }
}
