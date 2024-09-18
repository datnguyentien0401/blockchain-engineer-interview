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
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620016a3380380620016a383398181016040528101906200003791906200012b565b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505062000172565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000620000f382620000c6565b9050919050565b6200010581620000e6565b81146200011157600080fd5b50565b6000815190506200012581620000fa565b92915050565b60008060408385031215620001455762000144620000c1565b5b6000620001558582860162000114565b9250506020620001688582860162000114565b9150509250929050565b61152180620001826000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c8063402ff0db1461006757806350969f44146100975780635231f627146100c7578063a5bde23b146100e5578063b62fdfce14610115578063dab3761e14610131575b600080fd5b610081600480360381019061007c9190610a31565b61014f565b60405161008e9190610bbc565b60405180910390f35b6100b160048036038101906100ac9190610d13565b610289565b6040516100be9190610d6b565b60405180910390f35b6100cf610437565b6040516100dc9190610de5565b60405180910390f35b6100ff60048036038101906100fa9190610d13565b61045d565b60405161010c9190610e44565b60405180910390f35b61012f600480360381019061012a9190610e66565b6105b9565b005b610139610943565b6040516101469190610f56565b60405180910390f35b61015761098d565b60036000838152602001908152602001600020604051806080016040529081600082015481526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016002820180546101e590610fa0565b80601f016020809104026020016040519081016040528092919081815260200182805461021190610fa0565b801561025e5780601f106102335761010080835404028352916020019161025e565b820191906000526020600020905b81548152906001019060200180831161024157829003601f168201915b505050505081526020016003820160009054906101000a900460ff1615151515815250509050919050565b600060058260405161029b919061100d565b908152602001604051809103902060009054906101000a900460ff16156102f7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102ee90611081565b60405180910390fd5b60006103036000610969565b905061030f6000610977565b60405180608001604052808281526020013373ffffffffffffffffffffffffffffffffffffffff16815260200160405180602001604052806000815250815260200160001515815250600360008381526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020190816103d19190611243565b5060608201518160030160006101000a81548160ff0219169083151502179055509050507f698b35ede3baa51dbaa3b9a040c287690e40d0101d312c80eb364c7b17c458bc838260405161042692919061134e565b60405180910390a180915050919050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6104656109cd565b600482604051610475919061100d565b908152602001604051809103902060405180604001604052908160008201805461049e90610fa0565b80601f01602080910402602001604051908101604052809291908181526020018280546104ca90610fa0565b80156105175780601f106104ec57610100808354040283529160200191610517565b820191906000526020600020905b8154815290600101906020018083116104fa57829003601f168201915b5050505050815260200160018201805461053090610fa0565b80601f016020809104026020016040519081016040528092919081815260200182805461055c90610fa0565b80156105a95780601f1061057e576101008083540402835291602001916105a9565b820191906000526020600020905b81548152906001019060200180831161058c57829003601f168201915b5050505050815250509050919050565b60006003600084815260200190815260200160002090503373ffffffffffffffffffffffffffffffffffffffff168160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614610662576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610659906113ca565b60405180910390fd5b600586604051610672919061100d565b908152602001604051809103902060009054906101000a900460ff16156106ce576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106c590611081565b60405180910390fd5b8060030160009054906101000a900460ff1615610720576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161071790611436565b60405180910390fd5b838160020190816107319190611243565b50604051806040016040528087815260200186815250600487604051610757919061100d565b9081526020016040518091039020600082015181600001908161077a9190611243565b5060208201518160010190816107909190611243565b509050506000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166340d097c3336040518263ffffffff1660e01b81526004016107f19190611465565b6020604051808303816000875af1158015610810573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108349190611495565b9050866006600083815260200190815260200160002090816108569190611243565b50600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166321670f2233856040518363ffffffff1660e01b81526004016108b49291906114c2565b600060405180830381600087803b1580156108ce57600080fd5b505af11580156108e2573d6000803e3d6000fd5b5050505060016005886040516108f8919061100d565b908152602001604051809103902060006101000a81548160ff02191690831515021790555060018260030160006101000a81548160ff02191690831515021790555050505050505050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600081600001549050919050565b6001816000016000828254019250508190555050565b604051806080016040528060008152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001606081526020016000151581525090565b604051806040016040528060608152602001606081525090565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b610a0e816109fb565b8114610a1957600080fd5b50565b600081359050610a2b81610a05565b92915050565b600060208284031215610a4757610a466109f1565b5b6000610a5584828501610a1c565b91505092915050565b610a67816109fb565b82525050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610a9882610a6d565b9050919050565b610aa881610a8d565b82525050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610ae8578082015181840152602081019050610acd565b60008484015250505050565b6000601f19601f8301169050919050565b6000610b1082610aae565b610b1a8185610ab9565b9350610b2a818560208601610aca565b610b3381610af4565b840191505092915050565b60008115159050919050565b610b5381610b3e565b82525050565b6000608083016000830151610b716000860182610a5e565b506020830151610b846020860182610a9f565b5060408301518482036040860152610b9c8282610b05565b9150506060830151610bb16060860182610b4a565b508091505092915050565b60006020820190508181036000830152610bd68184610b59565b905092915050565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b610c2082610af4565b810181811067ffffffffffffffff82111715610c3f57610c3e610be8565b5b80604052505050565b6000610c526109e7565b9050610c5e8282610c17565b919050565b600067ffffffffffffffff821115610c7e57610c7d610be8565b5b610c8782610af4565b9050602081019050919050565b82818337600083830152505050565b6000610cb6610cb184610c63565b610c48565b905082815260208101848484011115610cd257610cd1610be3565b5b610cdd848285610c94565b509392505050565b600082601f830112610cfa57610cf9610bde565b5b8135610d0a848260208601610ca3565b91505092915050565b600060208284031215610d2957610d286109f1565b5b600082013567ffffffffffffffff811115610d4757610d466109f6565b5b610d5384828501610ce5565b91505092915050565b610d65816109fb565b82525050565b6000602082019050610d806000830184610d5c565b92915050565b6000819050919050565b6000610dab610da6610da184610a6d565b610d86565b610a6d565b9050919050565b6000610dbd82610d90565b9050919050565b6000610dcf82610db2565b9050919050565b610ddf81610dc4565b82525050565b6000602082019050610dfa6000830184610dd6565b92915050565b60006040830160008301518482036000860152610e1d8282610b05565b91505060208301518482036020860152610e378282610b05565b9150508091505092915050565b60006020820190508181036000830152610e5e8184610e00565b905092915050565b600080600080600060a08688031215610e8257610e816109f1565b5b600086013567ffffffffffffffff811115610ea057610e9f6109f6565b5b610eac88828901610ce5565b955050602086013567ffffffffffffffff811115610ecd57610ecc6109f6565b5b610ed988828901610ce5565b945050604086013567ffffffffffffffff811115610efa57610ef96109f6565b5b610f0688828901610ce5565b9350506060610f1788828901610a1c565b9250506080610f2888828901610a1c565b9150509295509295909350565b6000610f4082610db2565b9050919050565b610f5081610f35565b82525050565b6000602082019050610f6b6000830184610f47565b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680610fb857607f821691505b602082108103610fcb57610fca610f71565b5b50919050565b600081905092915050565b6000610fe782610aae565b610ff18185610fd1565b9350611001818560208601610aca565b80840191505092915050565b60006110198284610fdc565b915081905092915050565b600082825260208201905092915050565b7f446f6320616c7265616479206265656e207375626d6974746564000000000000600082015250565b600061106b601a83611024565b915061107682611035565b602082019050919050565b6000602082019050818103600083015261109a8161105e565b9050919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b6000600883026111037fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff826110c6565b61110d86836110c6565b95508019841693508086168417925050509392505050565b600061114061113b611136846109fb565b610d86565b6109fb565b9050919050565b6000819050919050565b61115a83611125565b61116e61116682611147565b8484546110d3565b825550505050565b600090565b611183611176565b61118e818484611151565b505050565b5b818110156111b2576111a760008261117b565b600181019050611194565b5050565b601f8211156111f7576111c8816110a1565b6111d1846110b6565b810160208510156111e0578190505b6111f46111ec856110b6565b830182611193565b50505b505050565b600082821c905092915050565b600061121a600019846008026111fc565b1980831691505092915050565b60006112338383611209565b9150826002028217905092915050565b61124c82610aae565b67ffffffffffffffff81111561126557611264610be8565b5b61126f8254610fa0565b61127a8282856111b6565b600060209050601f8311600181146112ad576000841561129b578287015190505b6112a58582611227565b86555061130d565b601f1984166112bb866110a1565b60005b828110156112e3578489015182556001820191506020850194506020810190506112be565b8683101561130057848901516112fc601f891682611209565b8355505b6001600288020188555050505b505050505050565b600061132082610aae565b61132a8185611024565b935061133a818560208601610aca565b61134381610af4565b840191505092915050565b600060408201905081810360008301526113688185611315565b90506113776020830184610d5c565b9392505050565b7f496e76616c69642073657373696f6e206f776e65720000000000000000000000600082015250565b60006113b4601583611024565b91506113bf8261137e565b602082019050919050565b600060208201905081810360008301526113e3816113a7565b9050919050565b7f53657373696f6e20697320656e64656400000000000000000000000000000000600082015250565b6000611420601083611024565b915061142b826113ea565b602082019050919050565b6000602082019050818103600083015261144f81611413565b9050919050565b61145f81610a8d565b82525050565b600060208201905061147a6000830184611456565b92915050565b60008151905061148f81610a05565b92915050565b6000602082840312156114ab576114aa6109f1565b5b60006114b984828501611480565b91505092915050565b60006040820190506114d76000830185611456565b6114e46020830184610d5c565b939250505056fea264697066735822122071d7b76e74d9e436caf7f002b7e75377596257e791d1cfe969126f8042c2940c64736f6c63430008130033\n";

    private static String librariesLinkedBinary;

    public static final String FUNC_CONFIRM = "confirm";

    public static final String FUNC_GENENFT = "geneNFT";

    public static final String FUNC_GETDOC = "getDoc";

    public static final String FUNC_GETSESSION = "getSession";

    public static final String FUNC_PCSPTOKEN = "pcspToken";

    public static final String FUNC_UPLOADDATA = "uploadData";

    public static final Event UPLOADDATA_EVENT = new Event("UploadData",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
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
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UPLOADDATA_EVENT, transactionReceipt);
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
            String proof, BigInteger sessionId, BigInteger riskScore) {
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
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<DataDoc> getDoc(String docId) {
        final Function function = new Function(FUNC_GETDOC,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(docId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DataDoc>() {}));
        return executeRemoteCallSingleValueReturn(function, DataDoc.class);
    }

    public RemoteFunctionCall<UploadSession> getSession(BigInteger sessionId) {
        final Function function = new Function(FUNC_GETSESSION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<UploadSession>() {}));
        return executeRemoteCallSingleValueReturn(function, UploadSession.class);
    }

    public RemoteFunctionCall<String> pcspToken() {
        final Function function = new Function(FUNC_PCSPTOKEN,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
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
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Controller(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Controller load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Controller(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Controller load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Controller(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Controller> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String nftAddress, String pcspAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, nftAddress),
                new org.web3j.abi.datatypes.Address(160, pcspAddress)));
        return deployRemoteCall(Controller.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Controller> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, String nftAddress, String pcspAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, nftAddress),
                new org.web3j.abi.datatypes.Address(160, pcspAddress)));
        return deployRemoteCall(Controller.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Controller> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String nftAddress, String pcspAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, nftAddress),
                new org.web3j.abi.datatypes.Address(160, pcspAddress)));
        return deployRemoteCall(Controller.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Controller> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, String nftAddress, String pcspAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, nftAddress),
                new org.web3j.abi.datatypes.Address(160, pcspAddress)));
        return deployRemoteCall(Controller.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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
