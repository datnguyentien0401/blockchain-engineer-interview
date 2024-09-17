const { ethers } = require("hardhat");

async function main() {
  const [deployer] = await ethers.getSigners();

  console.log("Deploying contracts with the account:", deployer.address);

  // Deploy GeneNFT
  const GeneNFT = await ethers.getContractFactory("GeneNFT");
  const geneNFT = await GeneNFT.deploy();
  console.log("GeneNFT deployed to:", geneNFT.target);

  // Deploy PCSP
  const PCSPToken = await ethers.getContractFactory("PostCovidStrokePrevention");
  const pcspToken = await PCSPToken.deploy();
  console.log("PCSP deployed to:", pcspToken.target);

  // Deploy Controller
  const Controller = await ethers.getContractFactory("Controller");
  const controller = await Controller.deploy(geneNFT.target, pcspToken.target);
  console.log("Controller deployed to:", controller.target);
}

main().catch((error) => {
  console.error("Deployment Error:", error);
  process.exit(1);
});
