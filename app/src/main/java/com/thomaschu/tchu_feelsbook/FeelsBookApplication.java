package com.thomaschu.tchu_feelsbook;

import android.app.Application;

public class FeelsBookApplication extends Application {

    /* singletons within our application (Model and Controller) */
    transient private static EmotionsModel emotionsModel = null;
    transient private static EmotionsController emotionsController = null;

    /*
    * getEmotionsModel will return our singleton and if none is found,
    * will create a new one and return it
    * @params {}
    * @return {EmotionsModel} our singleton instance
    * */
    public static EmotionsModel getEmotionsModel() {
        if(emotionsModel == null) {
            emotionsModel = new EmotionsModel();
        }
        return emotionsModel;
    }

    /*
     * getEmotionsController will return our singleton and if none is found,
     * will create a controller and model and return controller
     * @params {}
     * @return {EmotionsController} our singleton instance
     * */
    public static EmotionsController getEmotionsController() {
        if(emotionsController == null) {
            emotionsController = new EmotionsController(getEmotionsModel());
        }
        return emotionsController;
    }


    /*
    * onCreate will create a new model and controller and import the emotions from
    * our file of saved emotions from previous interactions of the app
    * */
    @Override
    public void onCreate() {
        super.onCreate();
        FeelsBookApplication.getEmotionsController().importEmotions(this);
    }
}
