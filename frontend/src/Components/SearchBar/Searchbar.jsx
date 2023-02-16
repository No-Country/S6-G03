import React from "react";


function SearchBar() {
    return (
        <div className="p-3">
            {/* <input className="peer w-[856px] h-[65px] bg-[#D1E0FD] border-4 border-[#275ECD] rounded-[20px] text-[32]"
                type="text"
                placeholder="Electricista, Plomero, Albañil, Gasista, Etc">
            </input> */}

            <form>
                <label className="relative block">
                    <input
                        className="w-[856px] h-[65px] bg-[#D1E0FD] text-[24px] placeholder:font-italitc placeholder:text-[24px] border-4 border-[#275ECD] rounded-[20px] focus:outline-none"
                        placeholder="Electricista, Plomero, Albañil, Gasista, Etc" 
                        type="text" />

                    <span className="absolute inset-y-0 right-0 flex items-center pr-3">
                        <img src="../../../src/Images/Search.png" alt="" />
                    </span>
                </label>
            </form>
        </div>


    )
}

export default SearchBar;