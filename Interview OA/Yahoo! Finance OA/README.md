## Running HTML on CoderPad

This pad is running a simple app that is served by Vite, and includes an HTML, CSS and JS file by default.  Changes are automatically captured as you type them, and other people in the Pad can see them.  You can add as many files to the project as you need, as well as any NPM packages.

To get started, edit the `index.html` file, and see your changes reload on the right.

### TypeScript

The app is pre-configured to support TypeScript, but you can define .js and .jsx files instead.

### IntelliSense

IntelliSense is running across your entire project, allowing you to see when there are syntax errors or to get quick hints for how to resolve errors or TypeScript issues.

### Shell

A shell is provided to you so you can inspect your container in more detail.  The shell can be used to install NPM packages using `npm install <package>`.  In addition to installing packages, the shell can be used for executing a test suite if you have one defined.

**Note: while it's possible to edit files directly from the shell, we recommend using the editor to make your changes.  That way, other people in the Pad can see your changes as they're being made.**

### Hot Module Reloading

Vite provides Hot Module Reloading by default, meaning that changes you make to the files in your project are automatically applied (after a 2 second debounce); there is no need to refresh the iframe to see the changes.  Vite will display any errors directly in the application output, or if there is a system-wide error, in the Logs.

### About Vite

We chose [Vite](https://vitejs.dev) because of its [fast startup times](​​https://vitejs.dev/guide/why.html#slow-server-start) and [quick browser updates](https://vitejs.dev/guide/why.html#slow-updates) using native ES Modules.  You probably won't need to change any of the [Vite configuration options](https://vitejs.dev/config/), but if you do, you can edit the `vite.config.ts` file.

### Container Limits

The container running your application has a few limitations.  Currently, we don't limit your CPU usage, though this may change in future.  In addition to CPU, we monitor the network bandwidth that is consumed, and limit you to 75mb for the duration of the container.  Finally, we limit the amount of memory accessible to each container to 2 GB.

### Assets

Out-of-the-box support for SVG files is included, just add a `whatever.svg` file and then import it wherever you need it.  Other assets will need to be hosted elsewhere and fetched via the shell, or just referenced directly in HTML tags.
