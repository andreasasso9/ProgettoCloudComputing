
function getSong(name) {
    fetch('/song/get?name=' + encodeURIComponent(name))
        .then(response => {
            if (!response.ok) {
                throw new Error('Errore nel recupero del file audio');
            }
            return response.json();
        })
        .then(songs => {
            let ul = document.getElementById("song-list");
            ul.innerHTML = ""; // Pulisce la lista prima di aggiungere nuove canzoni

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
        })
        .catch(error =>console.error('Errore:', error))
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
        let ul = document.getElementById("playlists");
        let li = document.createElement("li");
        li.innerHTML = `<a href="#">${name}</a>`;
        ul.appendChild(li);
        document.getElementById("playlistName").value = "";

        fetch("/playlist/create?name="+ encodeURIComponent(name)+"&email="+encodeURIComponent(email))
            .then(response=> {
                if (!response.ok) {
                    throw new Error('Errore nella creazione della playlist')
                }
            }).catch(e=>console.error(e))
        closeModal();
    } else {
        alert("Inserisci un nome valido!");
    }
}
