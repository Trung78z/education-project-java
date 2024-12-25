export interface NewPayload {
    id: string;
    title: string;
    description: string;
    content: string;
    image: string;
    publicNew: boolean;
    newCategory: {
        id: number;
        name: string
    };
    createdAt: string;
}
