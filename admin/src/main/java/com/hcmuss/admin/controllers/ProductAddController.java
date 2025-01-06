package com.hcmuss.admin.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hcmuss.admin.models.product.*;
import com.hcmuss.admin.utils.ApiResponse;
import com.hcmuss.admin.utils.JsonListResponse;
import com.hcmuss.admin.utils.LoadVariable;
import com.hcmuss.admin.utils.TokenStorage;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class ProductAddController {
    @FXML
    private TextField bodyField;

    @FXML
    private TextField colorField;

    @FXML
    private TextArea comfortConvenienceField;

    @FXML
    private TextField conditionField;

    @FXML
    private TextField cylindersField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField discountField;

    @FXML
    private TextField doorsField;

    @FXML
    private TextField driveTypeField;

    @FXML
    private TextField engineSizeField;

    @FXML
    private TextArea exteriorFeaturesField;

    @FXML
    private TextField fuelTankCapacityField;

    @FXML
    private TextField fuelTypeField;

    @FXML
    private TextField gearshiftField;

    @FXML
    private TextField grossVehicleWeightField;

    @FXML
    private TextField heightField;

    @FXML
    private TextArea interiorFeaturesField;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField luggageCapacitySeatsDownField;

    @FXML
    private TextField luggageCapacitySeatsUpField;

    @FXML
    private TextField maxLoadingWeightField;

    @FXML
    private TextField maxRoofLoadField;

    @FXML
    private TextField maxTowingWeightBrakedField;

    @FXML
    private TextField maxTowingWeightUnbrakedField;

    @FXML
    private TextField mileageField;

    @FXML
    private TextField minimumKerbweightField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberOfSeatsField;

    @FXML
    private TextField odometerField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField roofHeightField;

    @FXML
    private TextArea safetyFeaturesField;

    @FXML
    private Button saveButton;

    @FXML
    private Button selectFileButton;

    @FXML
    private TextField transmissionField;

    @FXML
    private TextField turningCircleField;

    @FXML
    private TextField typeField;

    @FXML
    private Text url_image;

    @FXML
    private TextField vinField;

    @FXML
    private TextField wheelbaseField;

    @FXML
    private TextField widthField;

    @FXML
    private TextField widthWithMirrorsField;

    @FXML
    private ComboBox<ProductBrand> branchComboBox;

    @FXML
    private TextField yearField;

    private String image;

    public void initialize() {
        fetchCategoriesFromApi();
    }

    @FXML
    void saveProduct(ActionEvent event) {
        String body = bodyField.getText();
        String color = colorField.getText();
        String comfortConvenience = comfortConvenienceField.getText();
        String condition = conditionField.getText();
        String cylinders = cylindersField.getText();
        String description = descriptionField.getText();
        String discount = discountField.getText();
        String doors = doorsField.getText();
        String driveType = driveTypeField.getText();
        String engineSize = engineSizeField.getText();
        String exteriorFeatures = exteriorFeaturesField.getText();
        String fuelTankCapacity = fuelTankCapacityField.getText();
        String fuelType = fuelTypeField.getText();
        String gearshift = gearshiftField.getText();
        String grossVehicleWeight = grossVehicleWeightField.getText();
        String height = heightField.getText();
        String interiorFeatures = interiorFeaturesField.getText();
        String length = lengthField.getText();
        String luggageCapacitySeatsDown = luggageCapacitySeatsDownField.getText();
        String luggageCapacitySeatsUp = luggageCapacitySeatsUpField.getText();
        String maxLoadingWeight = maxLoadingWeightField.getText();
        String maxRoofLoad = maxRoofLoadField.getText();
        String maxTowingWeightBraked = maxTowingWeightBrakedField.getText();
        String maxTowingWeightUnbraked = maxTowingWeightUnbrakedField.getText();
        String mileage = mileageField.getText();
        String minimumKerbweight = minimumKerbweightField.getText();
        String name = nameField.getText();
        String numberOfSeats = numberOfSeatsField.getText();
        String odometer = odometerField.getText();
        String price = priceField.getText();
        String quantity = quantityField.getText();
        String roofHeight = roofHeightField.getText();
        String safetyFeatures = safetyFeaturesField.getText();
        String transmission = transmissionField.getText();
        String turningCircle = turningCircleField.getText();
        String type = typeField.getText();
        String vin = vinField.getText();
        String wheelbase = wheelbaseField.getText();
        String width = widthField.getText();
        String widthWithMirrors = widthWithMirrorsField.getText();
        String year = yearField.getText();
        ProductBrand selectedCategory = branchComboBox.getValue();

        if (body == null || body.isEmpty() ||
                color == null || color.isEmpty() ||
                comfortConvenience == null || comfortConvenience.isEmpty() ||
                condition == null || condition.isEmpty() ||
                cylinders == null || cylinders.isEmpty() ||
                description == null || description.isEmpty() ||
                discount == null || discount.isEmpty() ||
                doors == null || doors.isEmpty() ||
                driveType == null || driveType.isEmpty() ||
                engineSize == null || engineSize.isEmpty() ||
                exteriorFeatures == null || exteriorFeatures.isEmpty() ||
                fuelTankCapacity == null || fuelTankCapacity.isEmpty() ||
                fuelType == null || fuelType.isEmpty() ||
                gearshift == null || gearshift.isEmpty() ||
                grossVehicleWeight == null || grossVehicleWeight.isEmpty() ||
                height == null || height.isEmpty() ||
                interiorFeatures == null || interiorFeatures.isEmpty() ||
                length == null || length.isEmpty() ||
                luggageCapacitySeatsDown == null || luggageCapacitySeatsDown.isEmpty() ||
                luggageCapacitySeatsUp == null || luggageCapacitySeatsUp.isEmpty() ||
                maxLoadingWeight == null || maxLoadingWeight.isEmpty() ||
                maxRoofLoad == null || maxRoofLoad.isEmpty() ||
                maxTowingWeightBraked == null || maxTowingWeightBraked.isEmpty() ||
                maxTowingWeightUnbraked == null || maxTowingWeightUnbraked.isEmpty() ||
                mileage == null || mileage.isEmpty() ||
                minimumKerbweight == null || minimumKerbweight.isEmpty() ||
                name == null || name.isEmpty() ||
                numberOfSeats == null || numberOfSeats.isEmpty() ||
                odometer == null || odometer.isEmpty() ||
                price == null || price.isEmpty() ||
                quantity == null || quantity.isEmpty() ||
                roofHeight == null || roofHeight.isEmpty() ||
                safetyFeatures == null || safetyFeatures.isEmpty() ||
                transmission == null || transmission.isEmpty() ||
                turningCircle == null || turningCircle.isEmpty() ||
                type == null || type.isEmpty() ||
                vin == null || vin.isEmpty() ||
                wheelbase == null || wheelbase.isEmpty() ||
                width == null || width.isEmpty() ||
                widthWithMirrors == null || widthWithMirrors.isEmpty() ||
                year == null || year.isEmpty() ||
                selectedCategory == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
            return;
        }

        try {
            Product product = new Product(
                    name,
                    Double.parseDouble(price),
                    Integer.parseInt(quantity),
                    image,
                    description,
                    odometer,
                    gearshift,
                    type,
                    Float.parseFloat(discount));
            ProductDimensionsCapacity dimensionsCapacity = new ProductDimensionsCapacity(
                    length,
                    height,
                    wheelbase,
                    roofHeight,
                    Integer.parseInt(luggageCapacitySeatsUp),
                    Integer.parseInt(luggageCapacitySeatsDown),
                    width,
                    widthWithMirrors,
                    Integer.parseInt(grossVehicleWeight),
                    Integer.parseInt(maxLoadingWeight),
                    Integer.parseInt(maxRoofLoad),
                    Integer.parseInt(numberOfSeats));

            ProductEngineAndTransmission engineAndTransmission = new ProductEngineAndTransmission(

                    Integer.parseInt(fuelTankCapacity),
                    Integer.parseInt(maxTowingWeightBraked),
                    Integer.parseInt(maxTowingWeightUnbraked),
                    Integer.parseInt(minimumKerbweight),
                    Integer.parseInt(turningCircle));

            ProductOverview productOverview = new ProductOverview(
                    body,
                    condition,
                    Integer.parseInt(mileage),
                    Double.parseDouble(engineSize),
                    fuelType,
                    Integer.parseInt(doors),
                    Integer.parseInt(year),
                    Integer.parseInt(cylinders),
                    transmission,
                    color,
                    driveType,
                    vin);

            List<ProductSafety> safetyFeaturesList = Arrays
                    .stream(safetyFeatures.isEmpty() ? new String[] { safetyFeatures } : safetyFeatures.split("\n"))
                    .map(ProductSafety::new)
                    .collect(Collectors.toList());

            List<ProductComfortConvenience> comfortConvenienceList = Arrays
                    .stream(comfortConvenience.isEmpty() ? new String[] { comfortConvenience }
                            : comfortConvenience.split("\n"))
                    .map(ProductComfortConvenience::new)
                    .collect(Collectors.toList());

            List<ProductInterior> interiorFeaturesList = Arrays
                    .stream(interiorFeatures.isEmpty() ? new String[] { interiorFeatures }
                            : interiorFeatures.split("\n"))
                    .map(ProductInterior::new)
                    .collect(Collectors.toList());

            List<ProductExterior> exteriorFeaturesList = Arrays
                    .stream(exteriorFeatures.isEmpty() ? new String[] { exteriorFeatures }
                            : exteriorFeatures.split("\n"))
                    .map(ProductExterior::new)
                    .collect(Collectors.toList());

            product.setOverview(productOverview);
            product.setDimensionsCapacity(dimensionsCapacity);
            product.setEngineAndTransmission(engineAndTransmission);

            product.setProductBrand(selectedCategory);

            product.setInterior(interiorFeaturesList);
            product.setExterior(exteriorFeaturesList);
            product.setSafety(safetyFeaturesList);
            product.setComfortConvenience(comfortConvenienceList);

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(product);

                HttpClient client = HttpClient.newHttpClient();
                String BaseUrl = LoadVariable.get("BASE_URL");

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(BaseUrl + "/api/v1/product"))
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + TokenStorage.getToken())
                        .POST(HttpRequest.BodyPublishers.ofString(json))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                ApiResponse apiResponse = objectMapper.readValue(response.body(), ApiResponse.class);
                boolean success = apiResponse.isSuccess();
                Object message = apiResponse.getMessage();

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("News added successfully!");
                    alert.showAndWait();

                    switchToDashboard();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to add news: " + message);
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    private void switchToDashboard() {
        try {
            FXMLLoader fxmlLoaderDashboard = new FXMLLoader(
                    getClass().getResource("/com/hcmuss/admin/fxml/dashboard.fxml"));
            Scene dashboardScene = new Scene(fxmlLoaderDashboard.load(), 1280, 768);
            Stage stage = (Stage) priceField.getScene().getWindow();
            stage.setScene(dashboardScene);
            stage.setTitle("Dashboard");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void closeProduct(ActionEvent event) {
        switchToDashboard();

    }

    @FXML
    private void addImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(selectFileButton.getScene().getWindow());
        if (selectedFile != null) {
            try {
                byte[] fileContent = Files.readAllBytes(selectedFile.toPath());
                String encodedString = Base64.getEncoder().encodeToString(fileContent);
                url_image.setText(selectedFile.getName());
                image = encodedString;
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("File Error", "Could not read the file.");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void fetchCategoriesFromApi() {
        String BaseUrl = LoadVariable.get("BASE_URL");

        String url = BaseUrl + "/api/v1/brand";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        new Thread(() -> {
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    if (httpResponse.statusCode() == 200) {
                        try {

                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.registerModule(new JavaTimeModule());

                            JsonListResponse<ProductBrand> parsedResponse = objectMapper.readValue(
                                    httpResponse.body(),
                                    new TypeReference<JsonListResponse<ProductBrand>>() {
                                    });

                            List<ProductBrand> categories = parsedResponse.getMessage();

                            ObservableList<ProductBrand> categoryList = FXCollections.observableArrayList(categories);

                            branchComboBox.setItems(categoryList);

                            branchComboBox.setCellFactory(_ -> new ListCell<>() {
                                @Override
                                protected void updateItem(ProductBrand item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty || item == null) {
                                        setText(null);
                                    } else {
                                        setText(item.getName());
                                    }
                                }
                            });
                            branchComboBox.setButtonCell(new ListCell<>() {
                                @Override
                                protected void updateItem(ProductBrand item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty || item == null) {
                                        setText(null);
                                    } else {
                                        setText(item.getName());
                                    }
                                }
                            });
                            if (!categoryList.isEmpty()) {
                                branchComboBox.getSelectionModel().select(0);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    e.printStackTrace();
                });
            }
        }).start();
    }

}
