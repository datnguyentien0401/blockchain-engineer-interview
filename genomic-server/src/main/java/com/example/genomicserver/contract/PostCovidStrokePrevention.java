package com.example.genomicserver.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/hyperledger/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.1.
 */
@SuppressWarnings("rawtypes")
public class PostCovidStrokePrevention extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b506040518060400160405280601c81526020017f506f73742d436f766964205374726f6b652050726576656e74696f6e000000008152506040518060400160405280600481526020017f504353500000000000000000000000000000000000000000000000000000000081525081600390816200008f9190620006e4565b508060049081620000a19190620006e4565b505050620000c4620000b86200021c60201b60201c565b6200022460201b60201c565b6200010433620000d9620002ea60201b60201c565b600a620000e791906200095b565b633b9aca00620000f89190620009ac565b620002f360201b60201c565b62000114620002ea60201b60201c565b600a6200012291906200095b565b613a98620001319190620009ac565b60066000600181526020019081526020016000208190555062000159620002ea60201b60201c565b600a6200016791906200095b565b610bb8620001769190620009ac565b6006600060028152602001908152602001600020819055506200019e620002ea60201b60201c565b600a620001ac91906200095b565b60e1620001ba9190620009ac565b600660006003815260200190815260200160002081905550620001e2620002ea60201b60201c565b600a620001f091906200095b565b601e620001fe9190620009ac565b60066000600481526020019081526020016000208190555062000ae3565b600033905090565b6000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905081600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b60006012905090565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff160362000365576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016200035c9062000a58565b60405180910390fd5b62000379600083836200046060201b60201c565b80600260008282546200038d919062000a7a565b92505081905550806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055508173ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef8360405162000440919062000ac6565b60405180910390a36200045c600083836200046560201b60201c565b5050565b505050565b505050565b600081519050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680620004ec57607f821691505b602082108103620005025762000501620004a4565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b6000600883026200056c7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff826200052d565b6200057886836200052d565b95508019841693508086168417925050509392505050565b6000819050919050565b6000819050919050565b6000620005c5620005bf620005b98462000590565b6200059a565b62000590565b9050919050565b6000819050919050565b620005e183620005a4565b620005f9620005f082620005cc565b8484546200053a565b825550505050565b600090565b6200061062000601565b6200061d818484620005d6565b505050565b5b8181101562000645576200063960008262000606565b60018101905062000623565b5050565b601f82111562000694576200065e8162000508565b62000669846200051d565b8101602085101562000679578190505b6200069162000688856200051d565b83018262000622565b50505b505050565b600082821c905092915050565b6000620006b96000198460080262000699565b1980831691505092915050565b6000620006d48383620006a6565b9150826002028217905092915050565b620006ef826200046a565b67ffffffffffffffff8111156200070b576200070a62000475565b5b620007178254620004d3565b6200072482828562000649565b600060209050601f8311600181146200075c576000841562000747578287015190505b620007538582620006c6565b865550620007c3565b601f1984166200076c8662000508565b60005b8281101562000796578489015182556001820191506020850194506020810190506200076f565b86831015620007b65784890151620007b2601f891682620006a6565b8355505b6001600288020188555050505b505050505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60008160011c9050919050565b6000808291508390505b60018511156200085957808604811115620008315762000830620007cb565b5b6001851615620008415780820291505b80810290506200085185620007fa565b945062000811565b94509492505050565b60008262000874576001905062000947565b8162000884576000905062000947565b81600181146200089d5760028114620008a857620008de565b600191505062000947565b60ff841115620008bd57620008bc620007cb565b5b8360020a915084821115620008d757620008d6620007cb565b5b5062000947565b5060208310610133831016604e8410600b8410161715620009185782820a905083811115620009125762000911620007cb565b5b62000947565b62000927848484600162000807565b92509050818404811115620009415762000940620007cb565b5b81810290505b9392505050565b600060ff82169050919050565b6000620009688262000590565b915062000975836200094e565b9250620009a47fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff848462000862565b905092915050565b6000620009b98262000590565b9150620009c68362000590565b9250828202620009d68162000590565b91508282048414831517620009f057620009ef620007cb565b5b5092915050565b600082825260208201905092915050565b7f45524332303a206d696e7420746f20746865207a65726f206164647265737300600082015250565b600062000a40601f83620009f7565b915062000a4d8262000a08565b602082019050919050565b6000602082019050818103600083015262000a738162000a31565b9050919050565b600062000a878262000590565b915062000a948362000590565b925082820190508082111562000aaf5762000aae620007cb565b5b92915050565b62000ac08162000590565b82525050565b600060208201905062000add600083018462000ab5565b92915050565b611c938062000af36000396000f3fe608060405234801561001057600080fd5b50600436106101165760003560e01c806370a08231116100a257806395d89b411161007157806395d89b41146102cd578063a457c2d7146102eb578063a9059cbb1461031b578063dd62ed3e1461034b578063f2fde38b1461037b57610116565b806370a0823114610259578063715018a61461028957806379cc6790146102935780638da5cb5b146102af57610116565b806323b872dd116100e957806323b872dd146101a3578063313ce567146101d357806339509351146101f157806340c10f191461022157806342966c681461023d57610116565b806306fdde031461011b578063095ea7b31461013957806318160ddd1461016957806321670f2214610187575b600080fd5b610123610397565b604051610130919061121f565b60405180910390f35b610153600480360381019061014e91906112da565b610429565b6040516101609190611335565b60405180910390f35b61017161044c565b60405161017e919061135f565b60405180910390f35b6101a1600480360381019061019c91906112da565b610456565b005b6101bd60048036038101906101b8919061137a565b6104d6565b6040516101ca9190611335565b60405180910390f35b6101db610505565b6040516101e891906113e9565b60405180910390f35b61020b600480360381019061020691906112da565b61050e565b6040516102189190611335565b60405180910390f35b61023b600480360381019061023691906112da565b610545565b005b61025760048036038101906102529190611404565b61055b565b005b610273600480360381019061026e9190611431565b61056f565b604051610280919061135f565b60405180910390f35b6102916105b7565b005b6102ad60048036038101906102a891906112da565b6105cb565b005b6102b76105eb565b6040516102c4919061146d565b60405180910390f35b6102d5610615565b6040516102e2919061121f565b60405180910390f35b610305600480360381019061030091906112da565b6106a7565b6040516103129190611335565b60405180910390f35b610335600480360381019061033091906112da565b61071e565b6040516103429190611335565b60405180910390f35b61036560048036038101906103609190611488565b610741565b604051610372919061135f565b60405180910390f35b61039560048036038101906103909190611431565b6107c8565b005b6060600380546103a6906114f7565b80601f01602080910402602001604051908101604052809291908181526020018280546103d2906114f7565b801561041f5780601f106103f45761010080835404028352916020019161041f565b820191906000526020600020905b81548152906001019060200180831161040257829003601f168201915b5050505050905090565b60008061043461084b565b9050610441818585610853565b600191505092915050565b6000600254905090565b61045e610a1c565b60018110158015610470575060048111155b6104af576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104a690611574565b60405180910390fd5b6000600660008381526020019081526020016000205490506104d18382610a9a565b505050565b6000806104e161084b565b90506104ee858285610bf0565b6104f9858585610c7c565b60019150509392505050565b60006012905090565b60008061051961084b565b905061053a81858561052b8589610741565b61053591906115c3565b610853565b600191505092915050565b61054d610a1c565b6105578282610a9a565b5050565b61056c61056661084b565b82610ef2565b50565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6105bf610a1c565b6105c960006110bf565b565b6105dd826105d761084b565b83610bf0565b6105e78282610ef2565b5050565b6000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b606060048054610624906114f7565b80601f0160208091040260200160405190810160405280929190818152602001828054610650906114f7565b801561069d5780601f106106725761010080835404028352916020019161069d565b820191906000526020600020905b81548152906001019060200180831161068057829003601f168201915b5050505050905090565b6000806106b261084b565b905060006106c08286610741565b905083811015610705576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106fc90611669565b60405180910390fd5b6107128286868403610853565b60019250505092915050565b60008061072961084b565b9050610736818585610c7c565b600191505092915050565b6000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b6107d0610a1c565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff160361083f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610836906116fb565b60405180910390fd5b610848816110bf565b50565b600033905090565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff16036108c2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108b99061178d565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1603610931576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109289061181f565b60405180910390fd5b80600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92583604051610a0f919061135f565b60405180910390a3505050565b610a2461084b565b73ffffffffffffffffffffffffffffffffffffffff16610a426105eb565b73ffffffffffffffffffffffffffffffffffffffff1614610a98576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a8f9061188b565b60405180910390fd5b565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1603610b09576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b00906118f7565b60405180910390fd5b610b1560008383611185565b8060026000828254610b2791906115c3565b92505081905550806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055508173ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef83604051610bd8919061135f565b60405180910390a3610bec6000838361118a565b5050565b6000610bfc8484610741565b90507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8114610c765781811015610c68576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c5f90611963565b60405180910390fd5b610c758484848403610853565b5b50505050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1603610ceb576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ce2906119f5565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1603610d5a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d5190611a87565b60405180910390fd5b610d65838383611185565b60008060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905081811015610deb576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610de290611b19565b60405180910390fd5b8181036000808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550816000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055508273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef84604051610ed9919061135f565b60405180910390a3610eec84848461118a565b50505050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1603610f61576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610f5890611bab565b60405180910390fd5b610f6d82600083611185565b60008060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905081811015610ff3576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610fea90611c3d565b60405180910390fd5b8181036000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555081600260008282540392505081905550600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040516110a6919061135f565b60405180910390a36110ba8360008461118a565b505050565b6000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905081600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b505050565b505050565b600081519050919050565b600082825260208201905092915050565b60005b838110156111c95780820151818401526020810190506111ae565b60008484015250505050565b6000601f19601f8301169050919050565b60006111f18261118f565b6111fb818561119a565b935061120b8185602086016111ab565b611214816111d5565b840191505092915050565b6000602082019050818103600083015261123981846111e6565b905092915050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061127182611246565b9050919050565b61128181611266565b811461128c57600080fd5b50565b60008135905061129e81611278565b92915050565b6000819050919050565b6112b7816112a4565b81146112c257600080fd5b50565b6000813590506112d4816112ae565b92915050565b600080604083850312156112f1576112f0611241565b5b60006112ff8582860161128f565b9250506020611310858286016112c5565b9150509250929050565b60008115159050919050565b61132f8161131a565b82525050565b600060208201905061134a6000830184611326565b92915050565b611359816112a4565b82525050565b60006020820190506113746000830184611350565b92915050565b60008060006060848603121561139357611392611241565b5b60006113a18682870161128f565b93505060206113b28682870161128f565b92505060406113c3868287016112c5565b9150509250925092565b600060ff82169050919050565b6113e3816113cd565b82525050565b60006020820190506113fe60008301846113da565b92915050565b60006020828403121561141a57611419611241565b5b6000611428848285016112c5565b91505092915050565b60006020828403121561144757611446611241565b5b60006114558482850161128f565b91505092915050565b61146781611266565b82525050565b6000602082019050611482600083018461145e565b92915050565b6000806040838503121561149f5761149e611241565b5b60006114ad8582860161128f565b92505060206114be8582860161128f565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061150f57607f821691505b602082108103611522576115216114c8565b5b50919050565b7f4e6f2072657761726420666f7220746865207269736b2073636f726500000000600082015250565b600061155e601c8361119a565b915061156982611528565b602082019050919050565b6000602082019050818103600083015261158d81611551565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006115ce826112a4565b91506115d9836112a4565b92508282019050808211156115f1576115f0611594565b5b92915050565b7f45524332303a2064656372656173656420616c6c6f77616e63652062656c6f7760008201527f207a65726f000000000000000000000000000000000000000000000000000000602082015250565b600061165360258361119a565b915061165e826115f7565b604082019050919050565b6000602082019050818103600083015261168281611646565b9050919050565b7f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160008201527f6464726573730000000000000000000000000000000000000000000000000000602082015250565b60006116e560268361119a565b91506116f082611689565b604082019050919050565b60006020820190508181036000830152611714816116d8565b9050919050565b7f45524332303a20617070726f76652066726f6d20746865207a65726f2061646460008201527f7265737300000000000000000000000000000000000000000000000000000000602082015250565b600061177760248361119a565b91506117828261171b565b604082019050919050565b600060208201905081810360008301526117a68161176a565b9050919050565b7f45524332303a20617070726f766520746f20746865207a65726f20616464726560008201527f7373000000000000000000000000000000000000000000000000000000000000602082015250565b600061180960228361119a565b9150611814826117ad565b604082019050919050565b60006020820190508181036000830152611838816117fc565b9050919050565b7f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572600082015250565b600061187560208361119a565b91506118808261183f565b602082019050919050565b600060208201905081810360008301526118a481611868565b9050919050565b7f45524332303a206d696e7420746f20746865207a65726f206164647265737300600082015250565b60006118e1601f8361119a565b91506118ec826118ab565b602082019050919050565b60006020820190508181036000830152611910816118d4565b9050919050565b7f45524332303a20696e73756666696369656e7420616c6c6f77616e6365000000600082015250565b600061194d601d8361119a565b915061195882611917565b602082019050919050565b6000602082019050818103600083015261197c81611940565b9050919050565b7f45524332303a207472616e736665722066726f6d20746865207a65726f20616460008201527f6472657373000000000000000000000000000000000000000000000000000000602082015250565b60006119df60258361119a565b91506119ea82611983565b604082019050919050565b60006020820190508181036000830152611a0e816119d2565b9050919050565b7f45524332303a207472616e7366657220746f20746865207a65726f206164647260008201527f6573730000000000000000000000000000000000000000000000000000000000602082015250565b6000611a7160238361119a565b9150611a7c82611a15565b604082019050919050565b60006020820190508181036000830152611aa081611a64565b9050919050565b7f45524332303a207472616e7366657220616d6f756e742065786365656473206260008201527f616c616e63650000000000000000000000000000000000000000000000000000602082015250565b6000611b0360268361119a565b9150611b0e82611aa7565b604082019050919050565b60006020820190508181036000830152611b3281611af6565b9050919050565b7f45524332303a206275726e2066726f6d20746865207a65726f2061646472657360008201527f7300000000000000000000000000000000000000000000000000000000000000602082015250565b6000611b9560218361119a565b9150611ba082611b39565b604082019050919050565b60006020820190508181036000830152611bc481611b88565b9050919050565b7f45524332303a206275726e20616d6f756e7420657863656564732062616c616e60008201527f6365000000000000000000000000000000000000000000000000000000000000602082015250565b6000611c2760228361119a565b9150611c3282611bcb565b604082019050919050565b60006020820190508181036000830152611c5681611c1a565b905091905056fea2646970667358221220ef907754b606a3b7e56a0fa1fbca5e533ec5d6a7278b89a3fa51efe86b524c6564736f6c63430008130033\n";

    private static String librariesLinkedBinary;

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_BURNFROM = "burnFrom";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_REWARD = "reward";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event APPROVAL_EVENT = new Event("Approval",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected PostCovidStrokePrevention(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PostCovidStrokePrevention(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PostCovidStrokePrevention(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PostCovidStrokePrevention(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalEventResponse> getApprovalEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ApprovalEventResponse getApprovalEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(APPROVAL_EVENT, log);
        ApprovalEventResponse typedResponse = new ApprovalEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getApprovalEventFromLog(log));
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipTransferredEventResponse getOwnershipTransferredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
        OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipTransferredEventFromLog(log));
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public static List<TransferEventResponse> getTransferEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TransferEventResponse getTransferEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRANSFER_EVENT, log);
        TransferEventResponse typedResponse = new TransferEventResponse();
        typedResponse.log = log;
        typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTransferEventFromLog(log));
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final Function function = new Function(FUNC_ALLOWANCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner),
                new org.web3j.abi.datatypes.Address(160, spender)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final Function function = new Function(
                FUNC_APPROVE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final Function function = new Function(FUNC_BALANCEOF,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger amount) {
        final Function function = new Function(
                FUNC_BURN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> burnFrom(String account, BigInteger amount) {
        final Function function = new Function(
                FUNC_BURNFROM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender,
            BigInteger subtractedValue) {
        final Function function = new Function(
                FUNC_DECREASEALLOWANCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender),
                new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender,
            BigInteger addedValue) {
        final Function function = new Function(
                FUNC_INCREASEALLOWANCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender),
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_MINT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> reward(String to, BigInteger riskScore) {
        final Function function = new Function(
                FUNC_REWARD,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to),
                new org.web3j.abi.datatypes.generated.Uint256(riskScore)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to,
            BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFERFROM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from),
                new org.web3j.abi.datatypes.Address(160, to),
                new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static PostCovidStrokePrevention load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PostCovidStrokePrevention(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PostCovidStrokePrevention load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PostCovidStrokePrevention(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PostCovidStrokePrevention load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PostCovidStrokePrevention(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PostCovidStrokePrevention load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PostCovidStrokePrevention(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PostCovidStrokePrevention> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PostCovidStrokePrevention.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<PostCovidStrokePrevention> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PostCovidStrokePrevention.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<PostCovidStrokePrevention> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PostCovidStrokePrevention.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<PostCovidStrokePrevention> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PostCovidStrokePrevention.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
