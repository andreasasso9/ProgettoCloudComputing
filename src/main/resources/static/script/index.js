
const user=JSON.parse(document.querySelector('meta[name="user"]').getAttribute('content'))
const queue=new Queue()
const songsGlobal=[]

function init() {
	applyStyle()
}

function createLikeButton(song) {
	let label = document.createElement('label')
	label.style.display = 'inline-flex'
	label.style.alignItems = 'center'
	label.style.justifyContent = 'center'
	label.style.cursor = 'pointer'
	label.style.fontSize = '24px'
	label.style.width = '40px'
	label.style.height = '40px'
	label.style.borderRadius = '50%'
	label.style.transition = 'background-color 0.3s ease, transform 0.2s ease'

	
	let checkbox = document.createElement('input')
	checkbox.type = 'checkbox'
	checkbox.style.display = 'none'

	let icon = document.createElement('span')
	if (user.favoriteSongs.includes(song.id)) {
		icon.textContent = '❤️'
		checkbox.checked = true
	}
	else {
		icon.textContent = '🤍'
		checkbox.checked = false
	}
	icon.style.transition = 'transform 0.2s ease'

	checkbox.addEventListener('change', () => {
		if (checkbox.checked) {
			label.style.transform = 'scale(1.1)'
			icon.textContent = '❤️'
			fetch('/user/addFavorite?email='+encodeURIComponent(user.email)+'&songId='+encodeURIComponent(song.id))
				.then(response => {
					if (!response.ok)
						return Promise.reject(response)
					user.favoriteSongs.push(song.id)
					return response.json()
				}).catch(error => console.error('Errore:', error))
		} else {
			label.style.transform = 'scale(1)'
			icon.textContent = '🤍'
			fetch('/user/removeFavorite?email='+encodeURIComponent(user.email)+'&songId='+encodeURIComponent(song.id))
				.then(response => {
					if (!response.ok)
						return Promise.reject(response)
					let index=user.favoriteSongs.indexOf(song.id)
					if (index > -1)
						user.favoriteSongs.splice(index, 1)
					return response.json()
				}).catch(error => console.error('Errore:', error))
		}
	})

	label.appendChild(checkbox)
	label.appendChild(icon)
	return label
}

function applyStyle() {
    let header = document.getElementById('header')

    let headerHeight = header.getBoundingClientRect().height

	let container=document.getElementsByClassName('container').item(0)
    container.style.top = headerHeight + "px"
}

function createSongsList(songs, ul) {
	songsGlobal.forEach(s => {
		songsGlobal.pop(s)
	})
	songsGlobal.push(...songs)

	ul.innerHTML = ""

	if (songs.length === 0) {
		let li = document.createElement("li")
		li.textContent = "Nessuna canzone trovata"
		li.style.color='white'
		ul.appendChild(li)
		return
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
		strong.style.color='white'
		span.appendChild(strong)

		let p=document.createElement('p')
		p.textContent=song.singer
		p.style.fontSize='70%'
		p.style.color='white'
		span.appendChild(p)

		let label=createLikeButton(song)

		div.append(span, label)
		li.appendChild(div)

		span.onclick=() => {
			queue.clear()
			let audioPlayer=document.getElementById('myAudio')
			audioPlayer.src=song.songUrl
			
			audioPlayer.play()

			let songInfo=document.getElementById('song-info')
			songInfo.innerHTML=''

			let infoName=document.createElement('p')
			infoName.id='info'
			infoName.textContent=song.name
			infoName.style.margin='0px 0px 5px 0px'

			let infoSinger=document.createElement('p')
			infoSinger.id='info-singer'
			infoSinger.textContent=song.singer
			infoSinger.style.margin='0'

			songInfo.append(infoName, infoSinger)
			songInfo.style.visibility='visible'


			queue.enqueue(songsGlobal.filter(s => s.id !== song.id))
			queue.shuffle()

			audioPlayer.addEventListener('ended', () => {
				if (!queue.isEmpty()) {
					let nextSong=queue.dequeue()
					audioPlayer.src=nextSong.songUrl
					audioPlayer.play()
				} else {
					queue.enqueue(songsGlobal)
					queue.shuffle()
					audioPlayer.src=queue.dequeue().songUrl
					audioPlayer.play()
				}
			})
		}

		ul.appendChild(li)
	})
}

function getSong(name) {
    console.log('name= ' + name)
    fetch('/song/get?name=' + encodeURIComponent(name))
        .then(response => {
            if (!response.ok)
                return Promise.reject(response)

            return response.json()
        }).then(songs => {
            let ul = document.getElementById("song-list")
            createSongsList(songs, ul)

        }).catch(error =>console.error('Errore:', error))
}

function toggleAside() {
	document.getElementById('aside').classList.toggle("toggle")
	document.getElementById('main').classList.toggle("toggle")
	document.getElementById('toggleAside').classList.toggle("toggle")
}

function getFavoriteSongs() {
	let email=JSON.parse(document.querySelector('meta[name="user"]').getAttribute('content')).email
	fetch('/user/getFavoriteSongs?email=' + encodeURIComponent(email))
		.then(response => {
			if (!response.ok)
				return Promise.reject(response)

			return response.json()
		}).then(songs => {
			let ul=document.getElementById('song-list')

			createSongsList(songs, ul)
		}).catch(error => console.error('Errore:', error))
}