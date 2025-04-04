class Queue {
	constructor() {
		this.queue = []
	}
	enqueue(songs) {
		if (!Array.isArray(songs)) {
			songs = [songs]
		}
		this.queue.push(...songs)
	}
	dequeue() {
		return this.queue.shift()
	}
	isEmpty() {
		return this.queue.length === 0
	}
	toString() {
		return this.queue.map(song => song.name).join(", ")
	}
	shuffle() {
		this.queue.sort(() => Math.random(Date.now) - 0.5)
	}
	clear() {
		this.queue = []
	}
}