require("@nomicfoundation/hardhat-toolbox");

/** @type import('hardhat/config').HardhatUserConfig */
module.exports = {
  solidity: "0.8.19",
  settings: {
    optimizer: {
      enabled: true,
      runs: 200,
    },
  },
  networks: {
    avalancheLocal: {
      url: 'http://localhost:9650/ext/bc/LIFENetwork/rpc',
      chainId: 9999,
      accounts: [`0x5ff2266cb45528d048c3356fa958682b293cfecb080573a292789ecd52afa8e7`]
    },
    avalancheFuji: {
      url: 'https://api.avax-test.network/ext/bc/C/rpc',
      chainId: 43113,
      accounts: [`0x5ff2266cb45528d048c3356fa958682b293cfecb080573a292789ecd52afa8e7`]
    },
    "optimism-sepolia": {
      url: "https://sepolia.infura.io/v3/",
      chainId: 11155111,
      accounts: [`0x5ff2266cb45528d048c3356fa958682b293cfecb080573a292789ecd52afa8e7`],
    }
  }
};
