export interface ProductPayload {
    id: number;
    name: string;
    price: number;
    quantity: number;
    image: string;
    description: string;
    odometer: string;
    gearshift: string;
    type: string;
    discount: number;
    productBrand: {
        id: number;
        name: string;
    };
    interior: Array<{
        id: number;
        name: string;
    }>;
    exterior: Array<{
        id: number;
        name: string;
    }>;
    safety: Array<{
        id: number;
        name: string;
    }>;
    comfortConvenience: Array<{
        id: number;
        name: string;
    }>;
    overview: {
        id: number;
        body: string;
        productCondition: string;
        mileage: number;
        engineSize: number;
        fuelType: string;
        doors: number;
        year: number;
        cylinders: number;
        transmission: string;
        color: string;
        driveType: string;
        vin: string;
        condition: string;
    };
    dimensionsCapacity: {
        id: number;
        length: string;
        height: string;
        wheelbase: string;
        heightWithRoofRails: string;
        luggageCapacitySeatsUp: number;
        luggageCapacitySeatsDown: number;
        width: string;
        widthWithMirrors: string;
        grossVehicleWeight: number;
        maxLoadingWeight: number;
        maxRoofLoad: number;
        numberOfSeats: number;
    };
    engineAndTransmission: {
        fuelTankCapacity: number;
        maxTowingWeightBraked: number;
        maxTowingWeightUnbraked: number;
        minimumKerbweight: number;
        turningCircleKerbToKerb: string;

    };
}
