package javafx_ui;

import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.javafx.MapView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class FXML_Homepage2Controller extends Application  {
@Override
    public void init() throws Exception {
        MapView.InitJavaFX();
    }

    @Override
    public void start(final Stage primaryStage) {
        final MapView mapView = new MapView();

        mapView.setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                    final Map map = mapView.getMap();
                    map.setZoom(5.0);
                    GeocoderRequest request = new GeocoderRequest();
                    request.setAddress("Kharkiv, UA");

                    mapView.getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
                                Marker marker = new Marker(map);
                                marker.setPosition(result[0].getGeometry().getLocation());

                                final InfoWindow window = new InfoWindow(map);
                                window.setContent("I did it!");
                                window.open(map, marker);
                            }
                        }
                    });
                }
            }
        });

        Scene scene = new Scene(new BorderPane(mapView), 700, 500);
        primaryStage.setTitle("JxMaps - Hello, World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}