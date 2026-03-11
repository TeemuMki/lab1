import java.awt.*;

public interface ModelObserver {
    void actOnModelUpdate();
    Point getCarDimension();
    Point getWorkshopDimension();
    Point getDrawpanelDimension();
}
