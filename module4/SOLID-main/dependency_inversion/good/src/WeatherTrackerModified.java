/**
 * Created by mrk on 4/8/14.
 */
public class WeatherTrackerModified {
    String currentConditions;
    public void setCurrentConditions(String weatherDescription) {
        this.currentConditions = weatherDescription;
    }
    
    public void notify(Notifier notifier) {
        notifier.alertWeatherConditions(currentConditions);
    }
}
