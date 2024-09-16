require("@nomicfoundation/hardhat-toolbox");

/** @type import('hardhat/config').HardhatUserConfig */
module.exports = {
  solidity: "0.8.19",
  networks: {
    avalancheLocal: {
      url: 'http://localhost:9650/ext/bc/LIFENetwork/rpc',
      accounts: [`0x5ff2266cb45528d048c3356fa958682b293cfecb080573a292789ecd52afa8e7`]
    }
  }
};
