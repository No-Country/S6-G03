import React from "react";
import SearchIcon from "../../../src/assets/images/Search.png";

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
            className="xs:w-[328px] xs:h-[40px] xs:rounded-[10px] xs:py-2.5 xs:px-2.5 xs:placeholder:text-[14px] md:w-[856px] md:h-[65px] bg-[#D1E0FD] md:text-[24px] md:px-5 md:placeholder:font-italitc md:placeholder:text-[24px] md:border-4 md:border-[#275ECD] md:rounded-[20px] md:focus:outline-none"
            placeholder="Electricista, Plomero, Albañil, Gasista, Etc"
            type="text"
          />

          <span className=" absolute inset-y-0 right-0 flex items-center pr-3">
            <img src={SearchIcon} alt="" />
          </span>
        </label>
      </form>
    </div>
  );
}

export default SearchBar;
