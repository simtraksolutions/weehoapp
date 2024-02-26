import Head from 'next/head'
import Image from 'next/image'
export default function Home() {

  return (
    <>
      <Head>
        <title>Rupali Bhattacharjee - Singer</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Header Section */}
        <header className="flex justify-between items-center py-6">
          <div className="flex items-center justify-center space-x-4">
            <Image src="/images/Weeho.png" alt="Logo" width={105} height={105} className="rounded-full" />
          </div>
          <h1 className="text-3xl font-bold flex justify-center">Rupali Bhattacharjee</h1>
          <div className="w-10 h-10">
            <Image src="/images/profile-icon.jpeg" alt="Profile Icon" width={40} height={40} className="rounded-full" />
          </div>
        </header>

        {/* Singer Profile Section */}
        <section className="text-center my-12">
          <Image src="/images/rupali.jpg" alt="Rupali Bhattacharjee" width={250} height={250} className="rounded-full mx-auto" />
          <h2 className="text-2xl font-semibold my-4 ">Greetings from WEEHO</h2>
          <p className="text-base text-gray-600 my-4">
            We would like to invite you for an amazing event Suron ki Shaam by Mrs. Rupali Bhattacharjee, a remarkable classical vocalist known for Tagore songs, Modern Bengali songs, Devotional songs and Bollywood Hindi songs with a voice that holds an undeniable charm, touching the souls of her listeners.
          </p>
          <a href="bookanevent.html" className="inline-block bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded-full my-4">Book an Event</a>
        </section>

        {/* About Naviin Gandharv Section */}
        <div className="bg-gray-100 rounded-lg p-6 my-12">
        <Image src="/images/rupali2.jpg" alt="Rupali Bhattacharjee" width={250} height={250} className="rounded-full object-cover mr-6" />
          <h2 className="text-2xl font-semibold my-4">Rupali Bhattacharjee</h2>
          <h3 className="text-lg text-gray-500">Modern Bengali, Devotional and Tagore song, Soloist</h3><br></br>
          <h2 className="text-lg text-blue-500 font-bold">About Rupali Bhattacharjee</h2>
          <p className="text-base text-gray-600 my-4">
            Introducing Mrs. Rupali Bhattacharjee: A Versatile Voice for Modern Bengali, Devotional, Tagore, and Hindi Songs. With a voice that captivates hearts and a talent that knows no boundaries, allow us to introduce the remarkable Mrs. Rupali Bhattacharjee. This versatile singer hails from the enchanting land of Bengal, where she has honed her skills to become a masterful performer in various genres.
          </p>
          <h2 className="text-lg text-blue-500 font-bold">Contribution in devotional songs</h2>
          <p className="text-base text-gray-600 my-4">
            Devotional songs are an area where Mrs. Rupali Bhattacharjee's spiritual connection shines through. With each soul-stirring verse, she transports her audience to a higher plane, evoking a sense of devotion and surrender. Her ability to infuse devotion into her voice is nothing short of magical, leaving listeners feeling spiritually awakened
          </p>
          <div className="my-4">
            <video controls className="mx-auto" width="500" height="auto">
              <source src="/images/rupalisingervideo.mp4" type="video/mp4" />
              Your browser does not support the video tag.
            </video>
          </div>
          <h2 className="text-lg text-blue-500 font-bold">Contribution in Modern Bengali songs</h2>
          <p className="text-base text-gray-600 my-4">
            Mrs. Rupali Bhattacharjee is a true musical maestro, gifted with the ability to effortlessly transition between Modern Bengali songs, Devotional melodies, Tagore classics, and Hindi compositions. Her voice holds an undeniable charm, capable of stirring emotions and touching the souls of her listeners. When she delves into Modern Bengali songs, Mrs. Bhattacharjee infuses her renditions with a contemporary flair, breathing new life into melodies that resonate with the modern generation. Her exquisite vocal control and emotional depth ensure that every note she sings leaves a lasting impact.
          </p>
          <h2 className="text-lg text-blue-500 font-bold">Connection with Rabindranath Tagore</h2>
          <p className="text-base text-gray-600 my-4">
            The works of Rabindranath Tagore hold a special place in Mrs. Bhattacharjee's heart. With a deep appreciation for the poetry and melodies created by the Nobel laureate, she brings Tagore's compositions to life with elegance and grace. Her renditions of these timeless classics carry the essence of Bengal's rich cultural heritage.
          </p>
          <h2 className="text-lg text-blue-500 font-bold">Magic of Versatility</h2>
          <p className="text-base text-gray-600 my-4">
            As a versatile artist, Mrs. Rupali Bhattacharjee also explores the realm of Hindi music. Her melodious voice effortlessly adapts to the nuances of Hindi songs, captivating audiences with her expressive and heartfelt performances. Whether it's a soulful ballad or an energetic dance number, she brings a unique touch to every Hindi composition. In the realm of music, Mrs. Rupali Bhattacharjee stands as a beacon of talent and versatility. With her enchanting voice, she seamlessly bridges the gap between traditional and contemporary genres, touching the hearts of fans across various musical landscapes. Experience the magic as Mrs. Bhattacharjee's melodies enchant your senses, taking you on a musical journey unlike any other.
          </p>
          <Image src="/images/rupalieventposter.jpg" alt="Rupali Bhattacharjee" width={700} height={700} className="mx-auto" />
        </div>
      </div>
    </>
  )
}