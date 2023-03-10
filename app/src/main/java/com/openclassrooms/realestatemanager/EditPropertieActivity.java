package com.openclassrooms.realestatemanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.realestatemanager.Model.Agents;
import com.openclassrooms.realestatemanager.Model.Photos;
import com.openclassrooms.realestatemanager.Model.Propertie;
import com.openclassrooms.realestatemanager.ViewModel.PropertieViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditPropertieActivity extends AppCompatActivity implements Serializable {

    PropertieViewModel propertieViewModel;
    private TextInputLayout type;
    private TextInputEditText typeTxt;
    private TextInputLayout price;
    private TextInputEditText priceTxt;
    private TextInputLayout state;
    private TextInputEditText stateTxt;
    private TextInputLayout description;
    private TextInputEditText descriptionTxt;
    private TextInputLayout address;
    private TextInputEditText addressTxt;
    private TextInputLayout totalRooms;
    private TextInputEditText roomsTxt;
    private TextInputLayout bathrooms;
    private TextInputEditText bathsTxt;
    private TextInputLayout bedrooms;
    private TextInputEditText bedsTxt;
    private TextInputLayout surface;
    private TextInputEditText surfaceTxt;
    private TextInputLayout soldDate;
    private TextInputEditText soldDateTxt;
    private CheckBox checkBox;
    private TextInputLayout agent;
    String agenturl;
    AutoCompleteTextView autoCompleteTextView;
    AgentAdapter adapter;
    private ArrayList<Agents> agentsList = new ArrayList<>();
    private TextView saveBtn;
    private List<Photos> photos = new ArrayList<>();
    private RecyclerView rv;
    private PropertieDetailMediaAdapter mediaAdapter = new PropertieDetailMediaAdapter(photos);
    private ImageView imageBtn;
    private Uri imageUri = null;
    private ImageView photo;
    private ImageView gallery;
    private ImageView picture;
    private TextInputLayout pictureTxt;
    private Chip school;
    private Chip hospital;
    private Chip supermarket;
    private Chip golf;
    private Chip park;
    private Chip casino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_propertie);

        configureViewModel();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_edit);

        setSupportActionBar(toolbar);

        TextView textView = (TextView)toolbar.findViewById(R.id.edit_txt_toolbar);
        textView.setText("Update property");

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        saveBtn = findViewById(R.id.edit_save);

        AgentAdapter adapter = new AgentAdapter(this, agentsList);

        autoCompleteTextView = findViewById(R.id.edittextState_agent);

        autoCompleteTextView.setAdapter(adapter);

        getDialog();

        populatePropertieInfos();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    soldDate.setVisibility(View.VISIBLE);
                } else {
                    soldDate.setVisibility(View.GONE);
                    soldDateTxt.setText("null");
                }
            }
        });
    }

    private void configureViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideVMF(this);
        this.propertieViewModel = new ViewModelProvider(this, viewModelFactory).get(PropertieViewModel.class);
    }



    private void getDialog() {
        imageBtn = findViewById(R.id.edit_propertie_image);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                alertDialog.setTitle("Choose the main image of the property");
                View v = getLayoutInflater().inflate(R.layout.pick_img_dialog, null);
                alertDialog.setView(v);



                photo = v.findViewById(R.id.add_photo_edit);
                gallery = v.findViewById(R.id.add_gallery_edit);
                picture = v.findViewById(R.id.photo);
                pictureTxt = v.findViewById(R.id.edit_propertie_image_details);

                photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i, 123);
                        picture.setVisibility(View.VISIBLE);
                    }
                });

                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        i.setType("image/*");
                        launchActivity.launch(i);
                        picture.setVisibility(View.VISIBLE);
                    }
                });

                alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundle = getIntent().getExtras();
                        Propertie propertie = (Propertie) bundle.getSerializable("edit");

                        long id = propertie.getId();

                        Photos photos = new Photos();
                        photos.setPropertyId(id);
                        photos.setUrl(imageUri.toString());
                        photos.setLegend(pictureTxt.getEditText().getText().toString());


                        propertieViewModel.addPhotos(photos);
                    }
                });

                alertDialog.show();
            }
        });
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            picture.setImageBitmap(photo);
            imageUri = writeToTempImageAndGetPathUri(getApplicationContext(), photo);

        }
    }

    private static Uri writeToTempImageAndGetPathUri(Context inContext, Bitmap inImage)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    ActivityResultLauncher<Intent> launchActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent datas = result.getData();
                    
                    if (datas != null && datas.getData() != null) {
                        imageUri = datas.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(), imageUri
                            );
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        picture.setImageBitmap(selectedImageBitmap);
                    }
                }
            }
    );


    private void getAgentAdapter() {
        this.propertieViewModel.getAllAgents().observe(this, value -> {
            agentsList.clear();
            agentsList.addAll(value);
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                agenturl = agentsList.get(i).getPhoto();
            }
        });
    }

    private void populatePropertieInfos() {
        bindViews();
        getAgentAdapter();

        Bundle bundle = getIntent().getExtras();
        Propertie propertie = (Propertie) bundle.getSerializable("edit");

        if (propertie.getSoldDate().contains("null")) {
            soldDate.setVisibility(View.GONE);
            checkBox.setChecked(false);
        } else {
            soldDate.setVisibility(View.VISIBLE);
            checkBox.setChecked(true);
        }

        typeTxt.setText(propertie.getType());
        priceTxt.setText(propertie.getPrice().toString());

        stateTxt.setText(propertie.getState());
        descriptionTxt.setText(propertie.getDescription());
        addressTxt.setText(propertie.getAddress());
        roomsTxt.setText(String.valueOf(propertie.getRooms()));
        bathsTxt.setText(String.valueOf(propertie.getBaths()));
        bedsTxt.setText(String.valueOf(propertie.getBedrooms()));
        surfaceTxt.setText(String.valueOf(propertie.getSurface()));
        soldDateTxt.setText(propertie.getSoldDate());

        school.setChecked(propertie.getSchool());
        hospital.setChecked(propertie.getHospital());
        supermarket.setChecked(propertie.getSupermarket());
        golf.setChecked(propertie.getGolf());
        park.setChecked(propertie.getPark());
        casino.setChecked(propertie.getCasino());

        autoCompleteTextView.setHint(propertie.getAgentName());

        rv = findViewById(R.id.media_list_edit);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        long id = propertie.getId();
        propertieViewModel.getAllPhotosByPropertiesId(id).observe(this, value -> {
           mediaAdapter.updateMedias(value);
        });


        rv.setAdapter(mediaAdapter);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String propertieType = type.getEditText().getText().toString();
                Integer propertiePrice = Integer.parseInt(price.getEditText().getText().toString());
                String propertieState = state.getEditText().getText().toString();
                String propertieDescription = description.getEditText().getText().toString();
                String propertieAddress = address.getEditText().getText().toString();
                Integer propertieRooms = Integer.parseInt(totalRooms.getEditText().getText().toString());
                Integer propertieBaths = Integer.parseInt(bathrooms.getEditText().getText().toString());
                Integer propertieBeds = Integer.parseInt(bedrooms.getEditText().getText().toString());
                Integer propertieSurface = Integer.parseInt(surface.getEditText().getText().toString());
                String propertieSoldDate = soldDate.getEditText().getText().toString();
                String propertieAgent = agent.getEditText().getText().toString();

                propertie.setType(propertieType);
                propertie.setPrice(propertiePrice);
                propertie.setState(propertieState);
                propertie.setDescription(propertieDescription);
                propertie.setAddress(propertieAddress);
                propertie.setRooms(propertieRooms);
                propertie.setBaths(propertieBaths);
                propertie.setBedrooms(propertieBeds);
                propertie.setSurface(propertieSurface);
                propertie.setSoldDate(propertieSoldDate);

                propertie.setHospital(hospital.isChecked());
                propertie.setSchool(school.isChecked());
                propertie.setSupermarket(supermarket.isChecked());
                propertie.setGolf(golf.isChecked());
                propertie.setPark(park.isChecked());
                propertie.setCasino(casino.isChecked());

                if (propertieAgent.isEmpty()) {
                    propertie.setAgentName(propertie.getAgentName());
                    propertie.setAgentPicture(propertie.getAgentPicture());
                } else {
                    propertie.setAgentName(propertieAgent);
                    propertie.setAgentPicture(agenturl);
                }
                propertieViewModel.updatePropertie(propertie);

                finish();

            }
        });

    }



    private void bindViews() {
        type = findViewById(R.id.edit_propertie_type);
        typeTxt = findViewById(R.id.edit_type_txt);
        price = findViewById(R.id.edit_propertie_price);
        priceTxt = findViewById(R.id.edit_price_txt);
        state = findViewById(R.id.edit_propertie_state);
        stateTxt = findViewById(R.id.edit_state_txt);
        description = findViewById(R.id.edit_propertie_description);
        descriptionTxt = findViewById(R.id.edit_description_txt);
        address = findViewById(R.id.edit_propertie_address);
        addressTxt = findViewById(R.id.edit_address_txt);
        totalRooms = findViewById(R.id.edit_propertie_rooms);
        roomsTxt = findViewById(R.id.edit_rooms_txt);
        bathrooms = findViewById(R.id.edit_propertie_baths);
        bathsTxt = findViewById(R.id.edit_baths_txt);
        bedrooms = findViewById(R.id.edit_propertie_bedrooms);
        bedsTxt = findViewById(R.id.edit_beds_txt);
        surface = findViewById(R.id.edit_propertie_surface);
        surfaceTxt = findViewById(R.id.edit_surface_txt);
        checkBox = findViewById(R.id.edit_sold_checkbox);
        soldDate = findViewById(R.id.edit_propertie_soldDate);
        soldDateTxt = findViewById(R.id.edit_soldDate_txt);
        agent = findViewById(R.id.edit_propertie_agent);
        school = findViewById(R.id.school);
        hospital = findViewById(R.id.hospital);
        supermarket = findViewById(R.id.supermarket);
        golf = findViewById(R.id.golf);
        park = findViewById(R.id.park);
        casino = findViewById(R.id.casino);
    }


}
