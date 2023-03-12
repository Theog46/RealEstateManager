package com.openclassrooms.realestatemanager.Database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.openclassrooms.realestatemanager.Database.dao.AgentsDao;
import com.openclassrooms.realestatemanager.Database.dao.PhotosDao;
import com.openclassrooms.realestatemanager.Database.dao.PropertieDao;
import com.openclassrooms.realestatemanager.Model.Agents;
import com.openclassrooms.realestatemanager.Model.Photos;
import com.openclassrooms.realestatemanager.Model.Propertie;

@Database(entities = {Propertie.class, Photos.class, Agents.class}, version = 1, exportSchema = false)
public abstract class RealEstateManagerDB extends RoomDatabase {

    private static volatile RealEstateManagerDB INSTANCE;
    public abstract PropertieDao propertieDao();
    public abstract PhotosDao photosDao();
    public abstract AgentsDao agentsDao();

    public static RealEstateManagerDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RealEstateManagerDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RealEstateManagerDB.class, "RealEstateManagerDB")
                            .allowMainThreadQueries()
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues propertiesContentValues = new ContentValues();

                propertiesContentValues.put("id", 1);
                propertiesContentValues.put("mainImg", "https://images7.alphacoders.com/341/341714.jpg");
                propertiesContentValues.put("type", "House");
                propertiesContentValues.put("state", "New York");
                propertiesContentValues.put("price", 272000);
                propertiesContentValues.put("surface", 164);
                propertiesContentValues.put("rooms", 9);
                propertiesContentValues.put("baths", 1);
                propertiesContentValues.put("bedrooms", 2);
                propertiesContentValues.put("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                propertiesContentValues.put("address", "5 Marconi St. Brooklyn, NY 11212");
                propertiesContentValues.put("entrydate", "14/02/2023");
                propertiesContentValues.put("solddate", "17/02/2023");
                propertiesContentValues.put("agentId", 1);
                propertiesContentValues.put("agentName", "Callum Holland");
                propertiesContentValues.put("agentPicture", "https://solarisstudios.com/wp-content/uploads/2019/07/Solaris2123_Wilson_030PS.jpg");
                propertiesContentValues.put("latitude", 37.426457);
                propertiesContentValues.put("longitude", -122.074504);
                propertiesContentValues.put("hospital", false);
                propertiesContentValues.put("school", true);
                propertiesContentValues.put("park", false);
                propertiesContentValues.put("casino", true);
                propertiesContentValues.put("supermarket", true);
                propertiesContentValues.put("golf", true);

                db.insert("Propertie", OnConflictStrategy.REPLACE, propertiesContentValues);

                propertiesContentValues.put("id", 2);
                propertiesContentValues.put("mainImg", "https://s2.best-wallpaper.net/wallpaper/2560x1600/1609/Villa-in-the-sea-house-living-room-lights-evening-resort_2560x1600.jpg");
                propertiesContentValues.put("type", "Villa");
                propertiesContentValues.put("state", "Caribbean");
                propertiesContentValues.put("price", 940000);
                propertiesContentValues.put("surface", 640);
                propertiesContentValues.put("rooms", 11);
                propertiesContentValues.put("baths", 2);
                propertiesContentValues.put("bedrooms", 3);
                propertiesContentValues.put("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                propertiesContentValues.put("address", "610 N 13TH ST SHELTON WA 98584-2100 USA");
                propertiesContentValues.put("entrydate", "29/10/2022");
                propertiesContentValues.put("solddate", "null");
                propertiesContentValues.put("agentId", 3);
                propertiesContentValues.put("agentName", "Zoe Lawson");
                propertiesContentValues.put("agentPicture", "https://www.caroll.com/dw/image/v2/BCMJ_PRD/on/demandware.static/-/Sites-Caroll_master/default/dw5c0eb4de/chemise-brenda-blanc-femme-vue2-35967719110681214.jpg?sw=400&sh=598");
                propertiesContentValues.put("latitude", 37.423384);
                propertiesContentValues.put("longitude", -122.075270);
                propertiesContentValues.put("hospital", true);
                propertiesContentValues.put("school", false);
                propertiesContentValues.put("park", false);
                propertiesContentValues.put("casino", false);
                propertiesContentValues.put("supermarket", true);
                propertiesContentValues.put("golf", false);

                db.insert("Propertie", OnConflictStrategy.REPLACE, propertiesContentValues);

                propertiesContentValues.put("id", 3);
                propertiesContentValues.put("mainImg", "https://wallpaperaccess.com/full/1142283.jpg");
                propertiesContentValues.put("type", "Penthouse");
                propertiesContentValues.put("state", "Los Angeles");
                propertiesContentValues.put("price", 614000);
                propertiesContentValues.put("surface", 96);
                propertiesContentValues.put("rooms", 7);
                propertiesContentValues.put("baths", 1);
                propertiesContentValues.put("bedrooms", 1);
                propertiesContentValues.put("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                propertiesContentValues.put("address", "1327 CRENSHAW BLVD LOS ANGELES CA 90019-4367 USA");
                propertiesContentValues.put("entrydate", "16/01/2023");
                propertiesContentValues.put("solddate", "24/01/2023");
                propertiesContentValues.put("agentId", 1);
                propertiesContentValues.put("agentName", "Callum Holland");
                propertiesContentValues.put("agentPicture", "https://solarisstudios.com/wp-content/uploads/2019/07/Solaris2123_Wilson_030PS.jpg");
                propertiesContentValues.put("latitude", 37.416130);
                propertiesContentValues.put("longitude", -122.087395);
                propertiesContentValues.put("hospital", false);
                propertiesContentValues.put("school", false);
                propertiesContentValues.put("park", true);
                propertiesContentValues.put("casino", true);
                propertiesContentValues.put("supermarket", false);
                propertiesContentValues.put("golf", false);

                db.insert("Propertie", OnConflictStrategy.REPLACE, propertiesContentValues);

                propertiesContentValues.put("id", 4);
                propertiesContentValues.put("mainImg", "https://wallpapercave.com/wp/wp3748854.jpg");
                propertiesContentValues.put("type", "House");
                propertiesContentValues.put("state", "Chicago");
                propertiesContentValues.put("price", 90666);
                propertiesContentValues.put("surface", 126);
                propertiesContentValues.put("rooms", 9);
                propertiesContentValues.put("baths", 2);
                propertiesContentValues.put("bedrooms", 3);
                propertiesContentValues.put("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                propertiesContentValues.put("address", "444 S FLOWER ST STE 555 CHICAGO CA 90071-2969 USA");
                propertiesContentValues.put("entrydate", "06/06/2022");
                propertiesContentValues.put("solddate", "null");
                propertiesContentValues.put("agentId", 3);
                propertiesContentValues.put("agentName", "Zoe Lawson");
                propertiesContentValues.put("agentPicture", "https://www.caroll.com/dw/image/v2/BCMJ_PRD/on/demandware.static/-/Sites-Caroll_master/default/dw5c0eb4de/chemise-brenda-blanc-femme-vue2-35967719110681214.jpg?sw=400&sh=598");
                propertiesContentValues.put("latitude", 37.422825);
                propertiesContentValues.put("longitude", -122.086412);
                propertiesContentValues.put("hospital", false);
                propertiesContentValues.put("school", false);
                propertiesContentValues.put("park", false);
                propertiesContentValues.put("casino", true);
                propertiesContentValues.put("supermarket", false);
                propertiesContentValues.put("golf", true);

                db.insert("Propertie", OnConflictStrategy.REPLACE, propertiesContentValues);

                propertiesContentValues.put("id", 5);
                propertiesContentValues.put("mainImg", "https://c4.wallpaperflare.com/wallpaper/893/25/187/design-the-city-style-interior-wallpaper-preview.jpg");
                propertiesContentValues.put("type", "Penthouse");
                propertiesContentValues.put("state", "San Francisco");
                propertiesContentValues.put("price", 1340000);
                propertiesContentValues.put("surface", 346);
                propertiesContentValues.put("rooms", 12);
                propertiesContentValues.put("baths", 2);
                propertiesContentValues.put("bedrooms", 4);
                propertiesContentValues.put("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                propertiesContentValues.put("address", "1327 CRENSHAW BLVD SAN FRANCISCO CA 90019-4367 USA");
                propertiesContentValues.put("entrydate", "12/02/2023");
                propertiesContentValues.put("solddate", "null");
                propertiesContentValues.put("agentId", 3);
                propertiesContentValues.put("agentName", "Zoe Lawson");
                propertiesContentValues.put("agentPicture", "https://www.caroll.com/dw/image/v2/BCMJ_PRD/on/demandware.static/-/Sites-Caroll_master/default/dw5c0eb4de/chemise-brenda-blanc-femme-vue2-35967719110681214.jpg?sw=400&sh=598");
                propertiesContentValues.put("latitude", 37.428610);
                propertiesContentValues.put("longitude", -122.083247);
                propertiesContentValues.put("hospital", true);
                propertiesContentValues.put("school", true);
                propertiesContentValues.put("park", false);
                propertiesContentValues.put("casino", true);
                propertiesContentValues.put("supermarket", false);
                propertiesContentValues.put("golf", true);

                db.insert("Propertie", OnConflictStrategy.REPLACE, propertiesContentValues);

                propertiesContentValues.put("id", 6);
                propertiesContentValues.put("mainImg", "https://www.febalcasa.com/wp-content/uploads/2020/06/Febal-Casa-Tavolino-Dialogo-di-design-152-153.jpg");
                propertiesContentValues.put("type", "Duplex");
                propertiesContentValues.put("state", "Philadelphia");
                propertiesContentValues.put("price", 656000);
                propertiesContentValues.put("surface", 83);
                propertiesContentValues.put("rooms", 4);
                propertiesContentValues.put("baths", 1);
                propertiesContentValues.put("bedrooms", 1);
                propertiesContentValues.put("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                propertiesContentValues.put("address", "1327 CRENSHAW BLVD SAN PHILADELPHIA CA 90019-4367 USA");
                propertiesContentValues.put("entrydate", "03/12/2022");
                propertiesContentValues.put("solddate", "28/12/2022");
                propertiesContentValues.put("agentId", 2);
                propertiesContentValues.put("agentName", "Jay Davidson");
                propertiesContentValues.put("agentPicture", "https://previews.123rf.com/images/deagreez/deagreez1512/deagreez151200423/50417647-portrait-de-vieux-bel-homme-d-affaires-dans-un-costume-%C3%A9l%C3%A9gant-avec-des-lunettes.jpg");
                propertiesContentValues.put("latitude", 37.424921);
                propertiesContentValues.put("longitude", -122.077989);
                propertiesContentValues.put("hospital", false);
                propertiesContentValues.put("school", false);
                propertiesContentValues.put("park", true);
                propertiesContentValues.put("casino", false);
                propertiesContentValues.put("supermarket", true);
                propertiesContentValues.put("golf", false);

                db.insert("Propertie", OnConflictStrategy.REPLACE, propertiesContentValues);

                propertiesContentValues.put("id", 7);
                propertiesContentValues.put("mainImg", "https://s2.best-wallpaper.net/wallpaper/3840x2160/1609/Wooden-villa-night-house-lights-garden_3840x2160.jpg");
                propertiesContentValues.put("type", "House");
                propertiesContentValues.put("state", "Washington");
                propertiesContentValues.put("price", 875000);
                propertiesContentValues.put("surface", 236);
                propertiesContentValues.put("rooms", 15);
                propertiesContentValues.put("baths", 3);
                propertiesContentValues.put("bedrooms", 4);
                propertiesContentValues.put("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                propertiesContentValues.put("address", "1327 CRENSHAW BLVD SAN WASHINGTON CA 90019-4367 USA");
                propertiesContentValues.put("entrydate", "15/10/2022");
                propertiesContentValues.put("solddate", "02/01/2023");
                propertiesContentValues.put("agentId", 2);
                propertiesContentValues.put("agentName", "Jay Davidson");
                propertiesContentValues.put("agentPicture", "https://previews.123rf.com/images/deagreez/deagreez1512/deagreez151200423/50417647-portrait-de-vieux-bel-homme-d-affaires-dans-un-costume-%C3%A9l%C3%A9gant-avec-des-lunettes.jpg");
                propertiesContentValues.put("latitude", 37.420950);
                propertiesContentValues.put("longitude", -122.078150);
                propertiesContentValues.put("hospital", false);
                propertiesContentValues.put("school", false);
                propertiesContentValues.put("park", false);
                propertiesContentValues.put("casino", true);
                propertiesContentValues.put("supermarket", false);
                propertiesContentValues.put("golf", true);

                db.insert("Propertie", OnConflictStrategy.REPLACE, propertiesContentValues);

                propertiesContentValues.put("id", 8);
                propertiesContentValues.put("mainImg", "https://www.abritel.fr/guides-voyage/wp-content/uploads/6jxY1ESkx28Keu8wI2EG8G/90d978451529ea3306d9506869703f55/pool-691008.jpg");
                propertiesContentValues.put("type", "Villa");
                propertiesContentValues.put("state", "Houston");
                propertiesContentValues.put("price", 1740000);
                propertiesContentValues.put("surface", 654);
                propertiesContentValues.put("rooms", 21);
                propertiesContentValues.put("baths", 3);
                propertiesContentValues.put("bedrooms", 6);
                propertiesContentValues.put("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
                propertiesContentValues.put("address", "1327 CRENSHAW BLVD SAN HOUSTON CA 90019-4367 USA");
                propertiesContentValues.put("entrydate", "16/02/2023");
                propertiesContentValues.put("solddate", "null");
                propertiesContentValues.put("agentId", 1);
                propertiesContentValues.put("agentName", "Callum Holland");
                propertiesContentValues.put("agentPicture", "https://solarisstudios.com/wp-content/uploads/2019/07/Solaris2123_Wilson_030PS.jpg");
                propertiesContentValues.put("latitude", 37.416430);
                propertiesContentValues.put("longitude", -122.078243);
                propertiesContentValues.put("hospital", true);
                propertiesContentValues.put("school", true);
                propertiesContentValues.put("park", false);
                propertiesContentValues.put("casino", false);
                propertiesContentValues.put("supermarket", false);
                propertiesContentValues.put("golf", true);

                db.insert("Propertie", OnConflictStrategy.REPLACE, propertiesContentValues);

                ContentValues photosContentValues = new ContentValues();

                photosContentValues.put("id", 1);
                photosContentValues.put("propertyId", 1);
                photosContentValues.put("url", "https://media.ma.cuisinella/-/media/bynder/cuisinella/2022/12/07/09/48/cla_cui_resource_viking_color_sauge_pimp_feuillage_2/16x9-cla_cui_resource_viking_color_sauge_pimp_feuillage_2.ashx?as=1&w=750&rev=208a3bdd1b2249718dd15f8bfb6586ae&hash=BE684299CF2745F421B2F3118E1C3262");
                photosContentValues.put("legend", "cuisine");

                db.insert("Photos", OnConflictStrategy.REPLACE, photosContentValues);

                photosContentValues.put("id", 2);
                photosContentValues.put("propertyId", 1);
                photosContentValues.put("url", "https://www.deco.fr/sites/default/files/styles/article_970x500/public/2019-06/shutterstock_190731452.jpg?itok=D6Yv9kkF");
                photosContentValues.put("legend", "jardin");

                db.insert("Photos", OnConflictStrategy.REPLACE, photosContentValues);

                photosContentValues.put("id", 3);
                photosContentValues.put("propertyId", 2);
                photosContentValues.put("url", "https://www.amaviacollection.com/wp-content/uploads/2022/05/Villa-Gaia-1-scaled.jpeg");
                photosContentValues.put("legend", "Vue extérieur");

                db.insert("Photos", OnConflictStrategy.REPLACE, photosContentValues);

                ContentValues agentsContentValues = new ContentValues();

                agentsContentValues.put("id", 1);
                agentsContentValues.put("name", "Callum Holland");
                agentsContentValues.put("photo", "https://solarisstudios.com/wp-content/uploads/2019/07/Solaris2123_Wilson_030PS.jpg");

                db.insert("Agents", OnConflictStrategy.REPLACE, agentsContentValues);

                agentsContentValues.put("id", 2);
                agentsContentValues.put("name", "Jay Davidson");
                agentsContentValues.put("photo", "https://previews.123rf.com/images/deagreez/deagreez1512/deagreez151200423/50417647-portrait-de-vieux-bel-homme-d-affaires-dans-un-costume-%C3%A9l%C3%A9gant-avec-des-lunettes.jpg");

                db.insert("Agents", OnConflictStrategy.REPLACE, agentsContentValues);


                agentsContentValues.put("id", 3);
                agentsContentValues.put("name", "Zoe Lawson");
                agentsContentValues.put("photo", "https://www.caroll.com/dw/image/v2/BCMJ_PRD/on/demandware.static/-/Sites-Caroll_master/default/dw5c0eb4de/chemise-brenda-blanc-femme-vue2-35967719110681214.jpg?sw=400&sh=598");

                db.insert("Agents", OnConflictStrategy.REPLACE, agentsContentValues);


            }
        };
    }
}
