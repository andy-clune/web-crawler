# web-crawler
Engineering excercise for Builtit

## Build web-crawler
Building the web crawler requires Maven.  

To build simply clone the repo and enter:

```
mvn clean install
```

## Run web-crawler
The web-crawler requires Java 1.5 or higher.

To run enter:

```
java -jar target/web-crawler-0.0.1-SNAPSHOT.jar <target web site>
```

## Design decisions

I made the web crawling step separate from the generation of the site map.  This made the code simpler to write, but it has some draw backs.  The biggest one is that the web crawler builds the entire site tree in memory.  For a large site that could become a problem, though I doubt that would ever be the case.  What annoys me more is that it feels like I wrote search algorithm twice.  It might be possible to get a cleaner solution by merging the depth first search (DFS) algorithm and the web crawling algorithm.

I separated the site map generation code from the DFS algorithm by using a visitor(ish) pattern.  To be precise I used a hierarhical visitor variant that allows th visitor to keep track of where it's at in the tree better.  It also feels a little odd because it doesn't completely follow the visitor pattern.  There is no "accept" method anywhere.  I debated moving the DFS algorithm into the Tree class.  I decided not to just to kee things simple. 

##  TODO

### Web crawler improvements
I decided to use Jsoup for screen scraping.  This was not a highly educated choice.  It seemed to be a popular API so I went with it.  It works great for simple cases, but I wonder if there are limitations.  The primary one I can think of is handling single page sites (e.g. pages powered by AngularJS).  I wonder if a more robust API may be better.

I force the cralwer to make the full directed graph sturcture into a tree.  I'm no sure is that's desiralbe or not.  I had to prevent it for recursing forever and that seemed to be the right way to go.  It also prepared it to be dumped to a site map nicely.

The actual crawling algorithm is functional, but ugly.  I feel like ti could be cleaned up.  I pondered trying to have the depth-first search algorithm do drive the crawling, but I decided that might complicate things too much for the purposed of this exercise.

I also don't like the method for checking the domain of a URI.  I actually built a URI object, which requires catching an exception, and used it to pull out the host.  A regex might work, but I just simply ran out of time to figure that out.

I did not unit test the crawler at all.  This is mostly because it would have been complicated to adequately test, and I was already well over my time budget for this exercise.

### Tree structure
Probably to simple.  Something that differentiates between nodes and edges might be smarter.  I also considered putting the search algorithm in the Tree class.  This would have made the visitor pattern a little more pure  I opted not to just to keep everything separate for now.

### General
*  Need to add proper exception handling.  Currently the code either eats the exception or dumps it to screen.  Not appropriate.
*  Add logging.
*  Add CLI support and input validation.
*  Better site map.  Should at least be easy to implement.
