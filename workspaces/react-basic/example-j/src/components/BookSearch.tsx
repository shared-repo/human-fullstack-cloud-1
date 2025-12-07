import axios from "axios"
import { useState } from "react"

export default function BookSearch() {

    const [books, setBooks] = useState("");

    const searchBook = async () => {
        // const url = "https://openapi.naver.com/v1/search/book.json";
        const url = "/naver/v1/search/book.json";
        const query = "리액트"
        const clientId = "7YSCeUowcuh9ttNjks58"
        const clientSecret = "5M8Rv9Jlj4"
        const response = await axios.get(`${url}?query=${query}`,{
            headers: {'X-Naver-Client-Id':clientId, 'X-Naver-Client-Secret': clientSecret}
        })
        setBooks( JSON.stringify(response.data) );
    }

    return (
        <>
            <button onClick={ searchBook }>책 검색</button>
            <hr />
            <div>
                { books ? books : "버튼을 눌러서 도서를 검색하세요" }
            </div>
            
        </>
    )
}