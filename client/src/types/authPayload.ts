export interface AuthPayload {
    token: string
}

export interface AuthLogin {
    username: string;
    password: string;

}
export interface AuthRegister {
    username: string;
    password: string;
    email: string;
}
export interface AuthChange {
    oldPassword: string;
    newPassword: string;
}