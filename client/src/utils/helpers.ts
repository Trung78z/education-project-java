export function formatContent(data: string, length: number) {
    if (data.length > length) {
        return data.substring(0, length) + "...";
    }
    return data;
}