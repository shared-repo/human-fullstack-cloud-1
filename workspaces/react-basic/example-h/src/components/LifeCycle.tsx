import { useEffect, useState } from "react";

export default function LifeCycle() {

    const [count, setCount] = useState(0);

    useEffect(() => {
        console.log(`Mounted`);
    }, []); // []을 사용하면 Mount될 때 한 번만 실행

    useEffect(() => {
        console.log(`Updated : ${count}`);
    }, [count]); // 배열에 포함된 값 중 어느 하나라도 변경되면 호출

    useEffect(() => {
        // useEffect 함수에서 반환되는 함수는 언마운트 시점에 호출됨
        return () => {
            console.log("Unmounted");
        };
    }, []);

    return (
        <>
            <h1>Count: {count} </h1>
            <button onClick={() => setCount((count) => count + 1)}>증가</button>
        </>
    );

}