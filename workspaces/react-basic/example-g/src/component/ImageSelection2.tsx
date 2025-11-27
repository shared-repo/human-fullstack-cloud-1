import { useState } from "react"

export default function ImageSelection() {

    const images = [1, 2, 3, 4, 5].map( (v) => `/assets/nature${v}.jpg` )

    const [imageSrc, setImageSrc] = useState(images[0])

    return (
        <>
            <table>
                <tr>
                    {
                        images.map( (v) => <td><img src={v} 
                                                    style={{ width: "170px" }}
                                                    onClick={ (event) => {
                                                        const element = event.target as HTMLImageElement
                                                        setImageSrc(element.src)
                                                    } } /></td> )
                    }
                </tr>
            </table>
            <img src={imageSrc} style={{width:"870px"}}></img>
        </>
    )

}