export interface station{

    //backend
    id: number,
    name: String,
    floor: String,
    status: String, //ATTENTION
    equipment: String,
    bookedByUserId: number,
    bookedByName: String,
    bookingStartTime: Date,
    bookingEndTime: Date,

    //enrichment
    location: {top: number, left: number}

}

export interface backendStation{
    id: number,
    name: String,
    floor: String,
    blocked: boolean,
    equipment: String,
    bookedByUserId: number,
    bookedByName: String,
    bookingStartTime: Date,
    bookingEndTime: Date
}