package com.example.genomicserver.contract;

import io.reactivex.Flowable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class Controller extends Contract {
    public static final String BINARY =
            "60806040523480156200001157600080fd5b50604051620016ab380380620016ab83398181016040528101906200003791906200012b565b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505062000172565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000620000f382620000c6565b9050919050565b6200010581620000e6565b81146200011157600080fd5b50565b6000815190506200012581620000fa565b92915050565b60008060408385031215620001455762000144620000c1565b5b6000620001558582860162000114565b9250506020620001688582860162000114565b9150509250929050565b61152980620001826000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c8063402ff0db1461006757806350969f44146100975780635231f627146100c7578063a5bde23b146100e5578063b62fdfce14610115578063dab3761e14610131575b600080fd5b610081600480360381019061007c9190610a39565b61014f565b60405161008e9190610bc4565b60405180910390f35b6100b160048036038101906100ac9190610d1b565b610289565b6040516100be9190610d73565b60405180910390f35b6100cf61046e565b6040516100dc9190610ded565b60405180910390f35b6100ff60048036038101906100fa9190610d1b565b610494565b60405161010c9190610e4c565b60405180910390f35b61012f600480360381019061012a9190610e6e565b6105f0565b005b61013961094b565b6040516101469190610f5e565b60405180910390f35b610157610995565b60036000838152602001908152602001600020604051806080016040529081600082015481526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016002820180546101e590610fa8565b80601f016020809104026020016040519081016040528092919081815260200182805461021190610fa8565b801561025e5780601f106102335761010080835404028352916020019161025e565b820191906000526020600020905b81548152906001019060200180831161024157829003601f168201915b505050505081526020016003820160009054906101000a900460ff1615151515815250509050919050565b600060058260405161029b9190611015565b908152602001604051809103902060009054906101000a900460ff16156102f7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102ee90611089565b60405180910390fd5b60006103036000610971565b905061030f600061097f565b60405180608001604052808281526020013373ffffffffffffffffffffffffffffffffffffffff16815260200160405180602001604052806000815250815260200160001515815250600360008381526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020190816103d1919061124b565b5060608201518160030160006101000a81548160ff02191690831515021790555090505060016005846040516104079190611015565b908152602001604051809103902060006101000a81548160ff0219169083151502179055507f698b35ede3baa51dbaa3b9a040c287690e40d0101d312c80eb364c7b17c458bc838260405161045d929190611356565b60405180910390a180915050919050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b61049c6109d5565b6004826040516104ac9190611015565b90815260200160405180910390206040518060400160405290816000820180546104d590610fa8565b80601f016020809104026020016040519081016040528092919081815260200182805461050190610fa8565b801561054e5780601f106105235761010080835404028352916020019161054e565b820191906000526020600020905b81548152906001019060200180831161053157829003601f168201915b5050505050815260200160018201805461056790610fa8565b80601f016020809104026020016040519081016040528092919081815260200182805461059390610fa8565b80156105e05780601f106105b5576101008083540402835291602001916105e0565b820191906000526020600020905b8154815290600101906020018083116105c357829003601f168201915b5050505050815250509050919050565b60006105fb86610494565b600001515114610640576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161063790611089565b60405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff166106608361014f565b6020015173ffffffffffffffffffffffffffffffffffffffff16146106ba576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106b1906113d2565b60405180910390fd5b6106c38261014f565b6060015115610707576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106fe9061143e565b60405180910390fd5b60405180604001604052808681526020018581525060048660405161072c9190611015565b9081526020016040518091039020600082015181600001908161074f919061124b565b506020820151816001019081610765919061124b565b509050506000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166340d097c3336040518263ffffffff1660e01b81526004016107c6919061146d565b6020604051808303816000875af11580156107e5573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610809919061149d565b90508560066000838152602001908152602001600020908161082b919061124b565b50600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166321670f2233846040518363ffffffff1660e01b81526004016108899291906114ca565b600060405180830381600087803b1580156108a357600080fd5b505af11580156108b7573d6000803e3d6000fd5b505050506040518060400160405280600781526020017f7375636365737300000000000000000000000000000000000000000000000000815250600360008581526020019081526020016000206002019081610913919061124b565b5060016003600085815260200190815260200160002060030160006101000a81548160ff021916908315150217905550505050505050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600081600001549050919050565b6001816000016000828254019250508190555050565b604051806080016040528060008152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001606081526020016000151581525090565b604051806040016040528060608152602001606081525090565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b610a1681610a03565b8114610a2157600080fd5b50565b600081359050610a3381610a0d565b92915050565b600060208284031215610a4f57610a4e6109f9565b5b6000610a5d84828501610a24565b91505092915050565b610a6f81610a03565b82525050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610aa082610a75565b9050919050565b610ab081610a95565b82525050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610af0578082015181840152602081019050610ad5565b60008484015250505050565b6000601f19601f8301169050919050565b6000610b1882610ab6565b610b228185610ac1565b9350610b32818560208601610ad2565b610b3b81610afc565b840191505092915050565b60008115159050919050565b610b5b81610b46565b82525050565b6000608083016000830151610b796000860182610a66565b506020830151610b8c6020860182610aa7565b5060408301518482036040860152610ba48282610b0d565b9150506060830151610bb96060860182610b52565b508091505092915050565b60006020820190508181036000830152610bde8184610b61565b905092915050565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b610c2882610afc565b810181811067ffffffffffffffff82111715610c4757610c46610bf0565b5b80604052505050565b6000610c5a6109ef565b9050610c668282610c1f565b919050565b600067ffffffffffffffff821115610c8657610c85610bf0565b5b610c8f82610afc565b9050602081019050919050565b82818337600083830152505050565b6000610cbe610cb984610c6b565b610c50565b905082815260208101848484011115610cda57610cd9610beb565b5b610ce5848285610c9c565b509392505050565b600082601f830112610d0257610d01610be6565b5b8135610d12848260208601610cab565b91505092915050565b600060208284031215610d3157610d306109f9565b5b600082013567ffffffffffffffff811115610d4f57610d4e6109fe565b5b610d5b84828501610ced565b91505092915050565b610d6d81610a03565b82525050565b6000602082019050610d886000830184610d64565b92915050565b6000819050919050565b6000610db3610dae610da984610a75565b610d8e565b610a75565b9050919050565b6000610dc582610d98565b9050919050565b6000610dd782610dba565b9050919050565b610de781610dcc565b82525050565b6000602082019050610e026000830184610dde565b92915050565b60006040830160008301518482036000860152610e258282610b0d565b91505060208301518482036020860152610e3f8282610b0d565b9150508091505092915050565b60006020820190508181036000830152610e668184610e08565b905092915050565b600080600080600060a08688031215610e8a57610e896109f9565b5b600086013567ffffffffffffffff811115610ea857610ea76109fe565b5b610eb488828901610ced565b955050602086013567ffffffffffffffff811115610ed557610ed46109fe565b5b610ee188828901610ced565b945050604086013567ffffffffffffffff811115610f0257610f016109fe565b5b610f0e88828901610ced565b9350506060610f1f88828901610a24565b9250506080610f3088828901610a24565b9150509295509295909350565b6000610f4882610dba565b9050919050565b610f5881610f3d565b82525050565b6000602082019050610f736000830184610f4f565b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680610fc057607f821691505b602082108103610fd357610fd2610f79565b5b50919050565b600081905092915050565b6000610fef82610ab6565b610ff98185610fd9565b9350611009818560208601610ad2565b80840191505092915050565b60006110218284610fe4565b915081905092915050565b600082825260208201905092915050565b7f446f6320616c7265616479206265656e207375626d6974746564000000000000600082015250565b6000611073601a8361102c565b915061107e8261103d565b602082019050919050565b600060208201905081810360008301526110a281611066565b9050919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b60006008830261110b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff826110ce565b61111586836110ce565b95508019841693508086168417925050509392505050565b600061114861114361113e84610a03565b610d8e565b610a03565b9050919050565b6000819050919050565b6111628361112d565b61117661116e8261114f565b8484546110db565b825550505050565b600090565b61118b61117e565b611196818484611159565b505050565b5b818110156111ba576111af600082611183565b60018101905061119c565b5050565b601f8211156111ff576111d0816110a9565b6111d9846110be565b810160208510156111e8578190505b6111fc6111f4856110be565b83018261119b565b50505b505050565b600082821c905092915050565b600061122260001984600802611204565b1980831691505092915050565b600061123b8383611211565b9150826002028217905092915050565b61125482610ab6565b67ffffffffffffffff81111561126d5761126c610bf0565b5b6112778254610fa8565b6112828282856111be565b600060209050601f8311600181146112b557600084156112a3578287015190505b6112ad858261122f565b865550611315565b601f1984166112c3866110a9565b60005b828110156112eb578489015182556001820191506020850194506020810190506112c6565b868310156113085784890151611304601f891682611211565b8355505b6001600288020188555050505b505050505050565b600061132882610ab6565b611332818561102c565b9350611342818560208601610ad2565b61134b81610afc565b840191505092915050565b60006040820190508181036000830152611370818561131d565b905061137f6020830184610d64565b9392505050565b7f496e76616c69642073657373696f6e206f776e65720000000000000000000000600082015250565b60006113bc60158361102c565b91506113c782611386565b602082019050919050565b600060208201905081810360008301526113eb816113af565b9050919050565b7f53657373696f6e20697320656e64656400000000000000000000000000000000600082015250565b600061142860108361102c565b9150611433826113f2565b602082019050919050565b600060208201905081810360008301526114578161141b565b9050919050565b61146781610a95565b82525050565b6000602082019050611482600083018461145e565b92915050565b60008151905061149781610a0d565b92915050565b6000602082840312156114b3576114b26109f9565b5b60006114c184828501611488565b91505092915050565b60006040820190506114df600083018561145e565b6114ec6020830184610d64565b939250505056fea26469706673582212203a094b63ad08d3b5aa3cdd7a7ee620fb6f2f0d8c924581f2775b97184577f93464736f6c63430008130033\n";

    private static String librariesLinkedBinary;

    public static final String FUNC_CONFIRM = "confirm";

    public static final String FUNC_GENENFT = "geneNFT";

    public static final String FUNC_GETDOC = "getDoc";

    public static final String FUNC_GETSESSION = "getSession";

    public static final String FUNC_PCSPTOKEN = "pcspToken";

    public static final String FUNC_UPLOADDATA = "uploadData";

    public static final Event UPLOADDATA_EVENT = new Event("UploadData",
                                                           Arrays.<TypeReference<?>>asList(
                                                                   new TypeReference<Utf8String>() {},
                                                                   new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Controller(String contractAddress, Web3j web3j, Credentials credentials,
                         BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Controller(String contractAddress, Web3j web3j, Credentials credentials,
                         ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Controller(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                         BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Controller(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                         ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<UploadDataEventResponse> getUploadDataEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UPLOADDATA_EVENT,
                                                                                          transactionReceipt);
        ArrayList<UploadDataEventResponse> responses = new ArrayList<UploadDataEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UploadDataEventResponse typedResponse = new UploadDataEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.docId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UploadDataEventResponse getUploadDataEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(UPLOADDATA_EVENT, log);
        UploadDataEventResponse typedResponse = new UploadDataEventResponse();
        typedResponse.log = log;
        typedResponse.docId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<UploadDataEventResponse> uploadDataEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUploadDataEventFromLog(log));
    }

    public Flowable<UploadDataEventResponse> uploadDataEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPLOADDATA_EVENT));
        return uploadDataEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> confirm(String docId, String contentHash,
                                                          String proof, BigInteger sessionId,
                                                          BigInteger riskScore) {
        final Function function = new Function(
                FUNC_CONFIRM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(docId),
                                    new org.web3j.abi.datatypes.Utf8String(contentHash),
                                    new org.web3j.abi.datatypes.Utf8String(proof),
                                    new org.web3j.abi.datatypes.generated.Uint256(sessionId),
                                    new org.web3j.abi.datatypes.generated.Uint256(riskScore)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> geneNFT() {
        final Function function = new Function(FUNC_GENENFT,
                                               Arrays.<Type>asList(),
                                               Arrays.<TypeReference<?>>asList(
                                                       new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<DataDoc> getDoc(String docId) {
        final Function function = new Function(FUNC_GETDOC,
                                               Arrays.<Type>asList(
                                                       new org.web3j.abi.datatypes.Utf8String(docId)),
                                               Arrays.<TypeReference<?>>asList(
                                                       new TypeReference<DataDoc>() {}));
        return executeRemoteCallSingleValueReturn(function, DataDoc.class);
    }

    public RemoteFunctionCall<UploadSession> getSession(BigInteger sessionId) {
        final Function function = new Function(FUNC_GETSESSION,
                                               Arrays.<Type>asList(
                                                       new org.web3j.abi.datatypes.generated.Uint256(
                                                               sessionId)),
                                               Arrays.<TypeReference<?>>asList(
                                                       new TypeReference<UploadSession>() {}));
        return executeRemoteCallSingleValueReturn(function, UploadSession.class);
    }

    public RemoteFunctionCall<String> pcspToken() {
        final Function function = new Function(FUNC_PCSPTOKEN,
                                               Arrays.<Type>asList(),
                                               Arrays.<TypeReference<?>>asList(
                                                       new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> uploadData(String docId) {
        final Function function = new Function(
                FUNC_UPLOADDATA,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(docId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Controller load(String contractAddress, Web3j web3j, Credentials credentials,
                                  BigInteger gasPrice, BigInteger gasLimit) {
        return new Controller(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Controller load(String contractAddress, Web3j web3j,
                                  TransactionManager transactionManager, BigInteger gasPrice,
                                  BigInteger gasLimit) {
        return new Controller(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Controller load(String contractAddress, Web3j web3j, Credentials credentials,
                                  ContractGasProvider contractGasProvider) {
        return new Controller(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Controller load(String contractAddress, Web3j web3j,
                                  TransactionManager transactionManager,
                                  ContractGasProvider contractGasProvider) {
        return new Controller(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Controller> deploy(Web3j web3j, Credentials credentials,
                                                ContractGasProvider contractGasProvider, String nftAddress,
                                                String pcspAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, nftAddress),
                                    new org.web3j.abi.datatypes.Address(160, pcspAddress)));
        return deployRemoteCall(Controller.class, web3j, credentials, contractGasProvider,
                                getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Controller> deploy(Web3j web3j, TransactionManager transactionManager,
                                                ContractGasProvider contractGasProvider, String nftAddress,
                                                String pcspAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, nftAddress),
                                    new org.web3j.abi.datatypes.Address(160, pcspAddress)));
        return deployRemoteCall(Controller.class, web3j, transactionManager, contractGasProvider,
                                getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Controller> deploy(Web3j web3j, Credentials credentials,
                                                BigInteger gasPrice, BigInteger gasLimit, String nftAddress,
                                                String pcspAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, nftAddress),
                                    new org.web3j.abi.datatypes.Address(160, pcspAddress)));
        return deployRemoteCall(Controller.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(),
                                encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Controller> deploy(Web3j web3j, TransactionManager transactionManager,
                                                BigInteger gasPrice, BigInteger gasLimit, String nftAddress,
                                                String pcspAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, nftAddress),
                                    new org.web3j.abi.datatypes.Address(160, pcspAddress)));
        return deployRemoteCall(Controller.class, web3j, transactionManager, gasPrice, gasLimit,
                                getDeploymentBinary(), encodedConstructor);
    }
    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class DataDoc extends DynamicStruct {
        public String id;

        public String hashContent;

        public DataDoc(String id, String hashContent) {
            super(new org.web3j.abi.datatypes.Utf8String(id),
                  new org.web3j.abi.datatypes.Utf8String(hashContent));
            this.id = id;
            this.hashContent = hashContent;
        }

        public DataDoc(Utf8String id, Utf8String hashContent) {
            super(id, hashContent);
            this.id = id.getValue();
            this.hashContent = hashContent.getValue();
        }
    }

    public static class UploadSession extends DynamicStruct {
        public BigInteger id;

        public String user;

        public String proof;

        public Boolean confirmed;

        public UploadSession(BigInteger id, String user, String proof, Boolean confirmed) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id),
                  new org.web3j.abi.datatypes.Address(160, user),
                  new org.web3j.abi.datatypes.Utf8String(proof),
                  new org.web3j.abi.datatypes.Bool(confirmed));
            this.id = id;
            this.user = user;
            this.proof = proof;
            this.confirmed = confirmed;
        }

        public UploadSession(Uint256 id, Address user, Utf8String proof, Bool confirmed) {
            super(id, user, proof, confirmed);
            this.id = id.getValue();
            this.user = user.getValue();
            this.proof = proof.getValue();
            this.confirmed = confirmed.getValue();
        }
    }

    public static class UploadDataEventResponse extends BaseEventResponse {
        public String docId;

        public BigInteger sessionId;
    }
}
