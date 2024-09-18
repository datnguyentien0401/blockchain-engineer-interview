pragma solidity ^0.8.9;

import "@openzeppelin/contracts/utils/Counters.sol";
import "./NFT.sol";
import "./Token.sol";

contract Controller {
    using Counters for Counters.Counter;

    //
    // STATE VARIABLES
    //
    Counters.Counter private _sessionIdCounter;
    GeneNFT public geneNFT;
    PostCovidStrokePrevention public pcspToken;

    struct UploadSession {
        uint256 id;
        address user;
        string proof;
        bool confirmed;
    }

    struct DataDoc {
        string id;
        string hashContent;
    }

    mapping(uint256 => UploadSession) sessions;
    mapping(string => DataDoc) docs;
    mapping(string => bool) docSubmits;
    mapping(uint256 => string) nftDocs;

    //
    // EVENTS
    //
    event UploadData(string docId, uint256 sessionId);

    constructor(address nftAddress, address pcspAddress) {
        geneNFT = GeneNFT(nftAddress);
        pcspToken = PostCovidStrokePrevention(pcspAddress);
    }

    function uploadData(string memory docId) public returns (uint256) {
        require(!docSubmits[docId], "Doc already been submitted");

        uint256 sessionId = _sessionIdCounter.current();
        _sessionIdCounter.increment();

        sessions[sessionId] = UploadSession({
            id: sessionId,
            user: msg.sender,
            proof: "",
            confirmed: false
        });

        docSubmits[docId] = true;

        emit UploadData(docId, sessionId);

        return sessionId;
    }

    function confirm(
        string memory docId,
        string memory contentHash,
        string memory proof,
        uint256 sessionId,
        uint256 riskScore
    ) public {
        // TODO: Implement this method: The proof here is used to verify that the result is returned from a valid computation on the gene data. For simplicity, we will skip the proof verification in this implementation. The gene data's owner will receive a NFT as a ownership certicate for his/her gene profile.
        require(bytes(getDoc(docId).id).length == 0, "Doc already been submitted");
        require(getSession(sessionId).user == msg.sender, "Invalid session owner");
        require(!getSession(sessionId).confirmed, "Session is ended");

        // TODO: Verify proof, we can skip this step

        // TODO: Update doc content
        docs[docId] = DataDoc({
            id: docId,
            hashContent : contentHash
        });

        // TODO: Mint NFT
        uint256 newNFTId = geneNFT.safeMint(msg.sender);
        nftDocs[newNFTId] = docId;

        // TODO: Reward PCSP token based on risk stroke
        pcspToken.reward(msg.sender, riskScore);

        // TODO: Close session
        sessions[sessionId].proof = "success";
        sessions[sessionId].confirmed = true;
    }

    function getSession(uint256 sessionId) public view returns(UploadSession memory) {
        return sessions[sessionId];
    }

    function getDoc(string memory docId) public view returns(DataDoc memory) {
        return docs[docId];
    }
}
