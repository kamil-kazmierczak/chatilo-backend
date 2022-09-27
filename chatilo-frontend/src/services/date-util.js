const formatEpochMillisToTime = (epochMillis) => {
    const date = new Date(epochMillis);

    const h = date.getHours().toString(10).padStart(2, '0');
    const m = date.getMinutes().toString(10).padStart(2, '0');
    const s = date.getSeconds().toString(10).padStart(2, '0');

    return h + ":" + m + ":" + s;
}


const DateUtil = {
    formatEpochMillisToTime
}

export default DateUtil;