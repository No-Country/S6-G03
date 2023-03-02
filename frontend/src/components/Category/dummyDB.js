export const professionals= [
        {
            id:1,
            firstName:"Alberto",
            lastName:"Paez",
            skills:["Plomeria", "Gasista"],
            profileImg:"https://media.istockphoto.com/id/178126106/photo/hot-water-heater-service.jpg?s=612x612&w=0&k=20&c=dQDPH9tY01tOfw8Fts22QoiCaevnMpekRlktgsPgZ_E=",
            title:"Plomero / Gasista Matriculado",
            description: "!Hola Comunidad! Soy beto soy plomero y gasista matriculado... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris placerat accumsan justo, at luctus nulla. Fusce luctus felis eget laoreet efficitur.",
            budget:1000,
            zone:"Lanús",
            reviews:[{
                id:"001",
                "title":"Excelente trabajo",
                "rating":4

            },
            {
                id:"002",
                "title":"buen trabajo 2",
                "rating":5
            },
            {
                id:"003",
                title:"regular trabajo ",
                rating:4
            }],
            jobs:[{
                title:"Reemplazo de Cañeria",
                date:"29/12/2022",
                zone:"Gerli",
                jobImg: "https://media.gettyimages.com/id/155432541/photo/plumber-hands-holding-wrench-and-fixing-a-sink-in-bathroom.jpg?s=612x612&w=gi&k=20&c=v6HT1gcTvEkk6EXanchlBrRyhuilMlMq5N06GvRVxFI=",
                client:"Susana Villagraña",
                review:"Estoy confirme con el servicio que me brindo beto. ademas dejo todo muy limpio",
                reviewID:"001"
            },
            {
                title:"Instalación de inodoro",
                date:"03/01/2023",
                zone:"Lanús",
                jobImg: "https://www.shutterstock.com/image-photo/plumber-work-bathroom-plumbing-repair-260nw-1066126052.jpg",
                client:"Stella Peris",
                review:"Estoy conforme con el servicio que me brindo beto. ademas dejo todo muy limpio",
                reviewID:"002"
            },
            
            ]
        },
    ]

    export const users= [
        {
            id:1,
            firstName:"Esteban",
            lastName:"Rivellota",
            address:{
                main: "Merlo 4061 - Remedios de Escalada CP 1826",
                street: "Sáenz Peña - De la Cruz"
            },
            phone:"11 6228-3220",
            zone:"Lanús",
        }
    ]
