
function getSong(name) {
    fetch('/song/get?name=' + encodeURIComponent(name))
        .then(response => {
            if (!response.ok)
                return Promise.reject(response)

            return response.json();
        }).then(songs => {
            let ul = document.getElementById("song-list");
            ul.innerHTML = "";

            if (songs.length === 0) {
                let li = document.createElement("li");
                li.textContent = "Nessuna canzone trovata";
                ul.appendChild(li);
                return;
            }

            songs.forEach(song => {
                let li = document.createElement("li")
                let div=document.createElement("div")

                div.style.display="flex"
                div.style.justifyContent="space-between"
                div.style.alignItems="center"
                div.style.width="100%"

                let span=document.createElement("span")
                let strong=document.createElement("strong")
                strong.textContent=song.name
                strong.style.fontSize='90%'
                span.appendChild(strong)

                let p=document.createElement('p')
                p.textContent=song.singer
                p.style.fontSize='70%'
                span.appendChild(p)

                let button=document.createElement('button')
                button.style.marginLeft="auto"
                button.style.padding="5px 10px"
                button.style.cursor="pointer"
                button.textContent='+'


                div.append(span, button)
                li.appendChild(div)

                span.onclick=() => {
                    let audioPlayer=document.getElementById('myAudio')
                    audioPlayer.src=song.songUrl
                    audioPlayer.play()
                }

                ul.appendChild(li);
            });

        }).catch(error =>console.error('Errore:', error))
}

function openModal() {
    document.getElementById("playlistModal").style.display = "block";
}

function closeModal() {
    document.getElementById("playlistModal").style.display = "none";
}

function addPlaylist() {
    let name = document.getElementById("playlistName").value.trim();
    if (name) {
        document.getElementById("playlistName").value = "";

        let user=document.querySelector('meta[name="user"]').getAttribute('content')
        fetch("/playlist/create?name="+ encodeURIComponent(name)+"&user="+encodeURIComponent(user))
            .then(response=> {
                if (!response.ok) {
                    throw new Error('Errore nella creazione della playlist')
                }
            }).catch(e=>console.error(e))
        closeModal();
        getPlaylists()
    } else {
        alert("Inserisci un nome valido!");
    }
}

function getPlaylists() {
    let user=document.querySelector('meta[name="user"]').getAttribute('content')
    let email=JSON.parse(user).email

    fetch('playlist/get?email='+encodeURIComponent(email))
        .then(response=> {
            if (!response.ok)
                Promise.reject(response)
            return response.json()
        }).then(playlists=>{
            let ul=document.getElementById('playlists')
            ul.innerHTML=''
            playlists.forEach(playlist=>{
                let li=document.createElement('li')
                li.textContent=playlist.name
                li.style.cursor='pointer'
                ul.appendChild(li)
            })
    }).catch(error=>console.error(error))
}
