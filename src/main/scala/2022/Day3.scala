package `2022`

import scala.util.matching.Regex
import scala.util.matching.Regex.Match

object Day3 {
  val allowed_chars = ('a' to 'z').toSet ++ ('A' to 'Z')

  def parseRucksacks(input: String): List[(Set[Char], Set[Char])] =
    val rucksack: List[String] = input.split("\n").map(_.trim().filter(allowed_chars)).filter(_.nonEmpty).toList
    val compartments: List[(String, String)] = rucksack.map(s => s.splitAt(s.length()/2))
    compartments.map{
      case (left, right) => (left.toCharArray().toSet, right.toCharArray().toSet)
    }

  val getCommonItem: ((Set[Char], Set[Char])) => Char = (left, right) =>
    (left & right).head

  val getCommonBadge: ((Set[Char], Set[Char]), (Set[Char], Set[Char]), (Set[Char], Set[Char])) => Char =
    (first, second, third) => ((first._1 ++ first._2) & (second._1 ++ second._2) & (third._1 ++ third._2)).head

  @scala.annotation.tailrec
  def getCommonBadges(rucksacks: Seq[(Set[Char], Set[Char])], parsedItems: Seq[Char] = Seq.empty[Char]): Seq[Char] =
    rucksacks match {
      case Seq(first, second, third, tail: _*) => getCommonBadges(tail,  parsedItems :+ getCommonBadge(first, second, third))
      case _ => parsedItems 
  }

  val convertCharToInt: Char => Int = {
    case c if c >= 'a' => c.toInt + 1 - 'a'
    case c => c.toInt + 1 - 'A' + 26
  }

  def getTotalScore(rucksacks: List[(Set[Char], Set[Char])]): Int =
    rucksacks.map(getCommonItem).map(convertCharToInt).sum
  
  def getTotalBadgeScore(rucksacks: List[(Set[Char], Set[Char])]): Int =
    getCommonBadges(rucksacks.toSeq).map(convertCharToInt).sum

}

object Day3Runner extends App {
  val input = """
PcPlnShmrLmBnmcwBhrmcmbHNGFGpwdFFwGNjNbGqNHH
tzQfRJfWZZztWzVtCTfRzFZjpFjNZjGLHbdHLDdjpb
CCQTzRLzvQVVfRzJfMPsnBlglgPmBgPmvSrl
RMfvbbszHTsssFPzDQPggpQJPQ
NSNcqVtLVGgDlpQBClVB
hmStGNNLhjNrpWLGSjWrZssbZTMMvTfMnThbRRTs
fTrTPGTbfftWBBmLjrJL
DqHwVMqVplDslmlZmpHVwNnShWZFdBBdjWBtWtdtWJSSLS
MNslpDvVHlwsmpQRgQgCfTTcvcRQ
pBBhRgDsMsswprBhvgRglZtFGFFRqZtZmRtNqtZPPN
TdmmzzmdZdqdGFtF
nmSccCVmSCpDCswMwl
NptqDsQtDTQzCvlzCpRlRp
jmZcndmjbZcjrmDvFMFFlwCvzFnF
jjgLVLrGcdDBNhWQTgHg
mLVhhfSMSTmMwClHGdpjDHjGdV
zPrZgJCgbsnrPtZzsCsbpRDjBRHnjGDRldRHppcG
JJrbsFrZqrgWbbqbrgWzJPNTwhTNCmmvfWCShhhmwwfm
ftgfljvgfgBTNvtggFDDGLGRDnMDzcQzncGt
VdbpbVdZwdwrsVVLRrMrDLDBGnBGcM
wmpWwWsHWBCCCPPvjvmSqlfTTmSNgN
jSqmzmmSSDRjLMLDwqjNcMMLTTflffWCCsRsTHnHVrfHWTsr
tdbgZpgBPdgGZGGFTHVpCsCVfVsJpnWl
FnPQFvbvhFFFbvBwScjhzcqSLLSzSN
bWdgrWwwFWbgzFWzrmNbdPqttChMSRnmqSPSnqtMRM
lcPJLDDPPfpMBCRJBtQtMh
lGDGjTGLLDHPPGjlPTsswsbHNFsNrFNFsrzr
VmtHfVhBLHVtlhphjZMdnQQZZqZmQDdzQQ
CPFwPWrvWgrfNgFPCMqZzMDDbznFTqqzDQ
NNPsfffPCsBLjpVltV
ssdBBJqJhlTJLsjTJqFFmnmmnnrcmpprmmmPcRlf
gqtqzSgWQWqmnRPPcNmmQM
GqbSVtGzvgvgWbZjjBhTdhBsTZBJBZ
jhNBsPDzLjsVhLSNzgvcvbcwbBWFcgtWCc
ZQQTTHHnGpMtnpdHpQJfMgrvWWFqbcWWGgrgwCCwwF
nHpmMnQQMmHpRnHRmMJnnTShPzljzjSNmSDhLsNSPtSh
GdqnBGFdlqzFnwdSCQZjZLLDZjZRvZLDVvgQ
PsptsTcftMfcTfhTghVDvvjnRNjVZnvV
WtPfJTfftJcMTrMnpccFwlCSCGFGCbCwJSbqBl
GjFLGhjRwFjNSjSdJCBBdQJddbBc
MVvMMHRzVtHlvlcQBQJHqdpQqCBC
vDgVztvvmrgrVRrMmsrsmZzZnWhGnNhGWTLfnLwTLhLTjngL
VljjQJSsrjjrCglsCjsgjVVfDLdZGMdvvGdQMzmvzcDQMc
HqPBtcpRWwtHbbFwBHZfmfpDfvffDfMfmGvM
PwHNbcwtqFqnwtNNqPNPPWBTThjhhVTCSJTThssVnSlJJV
GCccNCrrnCrpnzrnCDPcDDrvHHTBqTPhswqhPTBTTwBhTj
VfNmRtZgWWHdBdswdjZv
SmtQfgNmVFgVLVLVmrnMpcDLGCGLGDMpCp
CrdZdZmPPjrQdRPRDqDLBqBLBSWgWgLDzF
sQhTNphsVbhhhMJfhNVGqltVSzSllBzStlzFFFWB
hsMpwQhNMZmPmrwHRj
cNVpSVRpLHRLsVWWfnfsCshW
jvqjTgqZPlJZmbPPfbpswsPb
vlqdTZdtJvqdZjgqZrtRpQFtLFRQczHGzt
JJQndVQnQgTfNvGf
ljpbWbmNbDlGTvggGvZf
mpmRbMmmNDFDmScpzCsdzrnJrsCzrrnM
tNFtNFFzzjjzjBtVNZVbjZGlpSvTllpWwvnBlWGGBGCC
fPdcrrgPHrHMMMWlppGJSPwGSnGv
fmrqrhhfhdRddHrhQqQrfnLZjLtNttZjjRtzjFtRNj
sphRcpQRhfmnmfpptg
WVPlGLlSjCjSlGSHJJWZdmbmfvPmmnftbbgDdt
LJjjqVNjlnCTRcRhhsNcFF
vwwqttFjwgClRNCCvGNmZZMmJsPJjJpTdMpsZd
fBLVHHHrFnhHhnrVSTmfdPdPccTTPsMfsJ
QzVWzznzFbWNGNlt
vjMddVVmnWpdMndjvhhWfNLpfBsfLLZLBBSqqTZq
RFlrzQJPSRGzzzzgBZNsgBZTBflfgf
cQFDRHFDDGCJShCnvwVnnhCn
hgjlpRRLlPJJhTLJMDnwBndSPBNvMqnN
FGWVfZsmCbmVzrvtwCSMtMdnDMCw
VsVmVZfVQDmVFrrmzmGrHHTJgJjhHJcllglLQJRL
rrTVcTBgsjTffmfWHZTv
JLdnDlpGlGSLlpwJpHZfFvRZnWzWrHWqFH
wQDpDrdSlSCblCdwdSLlwQGBthPMsghNsVNVtCNNhNPjhs
CtCMvNhDMHfDDdffqtDtCflpJlBpvmWWJWwlpwFFvjwB
rGSbVGZrSsFJjlmBFZWp
rbbQgzVGrFVSPPGqfhftfqztNtqHtt
lMGZCGphllZDNshNNmHHND
PLwjVwJVsHmRrZZw
ffSdzjfZSjtjSjLtLLFFFGqFzznCpCnCBblQ
CqRnlzHCRWTlHPTZVQrcQtFsQFTcrQ
DfJcdBDBcftQjsrsBtjZ
JDfdGhSvNGhNfffGSfRznPvcRWcqCqmlvlcn
JPhBBBQCnCJCMhnhMZRrRZgbDgrWrNbglDgR
jLtSTwtsShwRNpRWrh
FLLSHsjGLGczvfPfJdfhddnHPC
BjHBNrWmTjFgJngbJhWd
vsGttMDtwCMQCJnqqqFJsggqdg
GFtDSwwMpTrzSSfcfm
rnWDQvpwWpDDcPjFPPHZjVDZ
CTJCRmCJcZZZHCCQ
LdlmdQJNpnLWbrfL
VdTdcVTZwCRGVGGMVmttlF
gnrsbngfgQSpBfpMBBBpSgMNNJbmGmlqGDqDNlFFJlGNFz
gprgQhgpMMMPsrRTCdPZwCwZZCRH
cHlCVGbbWHWqRNThhcNcmh
MwQDzpwdJwpBpPDQvrhShfLTTRLfLdjfNRqS
JwMBBrPsPDwQMDPPBPQJwMrvWHFbHHlgbsGnnWHnFnRGlblF
PQPjPDjRRQSFLSlgSmLlfh
zpLdBddbNCdqGbWJGWpJWWlsFsmmFpwfflFgfHwFhgmh
nJLdLVnzqqbjRctcPDQVTP
JdztScztPdSWLJLtgMbCjhvlbPRbjbMvCh
VZrqfQcFQwGVVFqfrTFTNqhljRHDMvMMGhRDRRHGbDhG
NZQNVQQpQmrZFQQFwQQVVZgBszJJgznstnmtcztdBSgs
nFHLNJzFbLJGGLMlTTRZbZRhWRTr
wVmgBBmtmwlqlWTwTM
sdvmgcPsCPPQQSMz
SccCqmQmgBmppLQmpSMjjlJzzsNPMDRbPNPlJM
VHZvwtZwhZHtdTwrVbNsljlRDlJPDhzsbN
dZwftVRftmcgpBCmBf
NTTlVlgNSflqbphFFhNbFp
wmmLmjwzwbWGLjRmtZZdhZLFtQQLQBFh
RvjbMjjvMzMWbDWwvzPjvmWSfVfsTlVVPVgTgPfVsnnnsJ
BsBsZHZNdWwsNdrzgCrMMqsjzzMC
flfhVWFmLrhQzCCh
fVbmFSpnSSmtnPZvdWbwvdvdHZ
NsZWWWWLsBZPhfsLmPhcFCCHCMMrqfqcvHMfHH
nThSllnplGlMpvFRcCqrrr
DnTwSztgzlDnVGTwztmdZhmLdJdNDshBdsWs
RBBGTFZGglMHvrtcgSdnNgjg
DmVcbmbJmwJDJzVVwzJfmfstnztvjnNjvNSpdptvzCnpjj
DsLcfLmbhVQssQJQscWRPBZZMMRLHFHZBGMG
FVvhVnhFnFhmvFhVcMBHLgcPClrqqrtqCppldrRRTppldg
QLWfDNwsQLtlrrCtDdpq
sJwZwLsGJWGGwzzWZNbWNLjQHSVhvHSnhcMFcbVmnvcchSBS
jTMNMrHBJWWDffRqfDBqfD
QmSFphtQqQmVmqVnPnPlpwgfnRnDPl
VqFmLFbLhmZhGFGmCmGtZLtJWzWHcJrNrHMccjMscMHzMZ
hGPGmbfPzbPfgdMdWGqBGQcqpp
nvFTvDrTdNZZlrjnMHHHpBBcppqq
rNlZZNLvRdRCRFFwZwhgbmSJPSmPfhfwhS
vjdbFWTtFRRvtvZZvdWJWbGjLhCcnrrrNqLNCPqchShNqc
QHQVlDsMfmmDMHDBdLdCSLnhNLNNfqCd
VQHsMDpHlzMBBwlsmMzmmlVwptvTWdvJdbvJtRTWgGFJJGtR
nSScBcnbbFSQVdBFBtWpwtvtPbTZthtTvT
pRzHpGjCDGzHGCGsThqqwZwPhCtvhTqZ
NzlzjDDpNldBFrlfFQ
qJlDlPPWppgppqPlplpfdvgnbMfGbdgCghMdCM
QWTWZcSsWbvVvTnhfC
tRFLwZrcrWzzlJmtBqlm
HMNMvvzzNcmfNmfbhs
qVcwCgjCLtWRSLsTPbmPfmTh
RtWCJgddWRtCJdWWgdBjwWWwpzMFpHGprcBGFFnGHQZHQGpF
gZgBDgDVGDGjmDZRtgjvVvtQdnLrcRcrdfdfCcnlscsJsn
WTqzqHqNzpHpwzNhMHNwWPbQCQcCLsnCrLLfcrffNflcNn
zHTwwpTPzTTwlFTFzwqzPbwZGgGZZBtmGGvGmBGZVFStFZ
znlSSzfzTcmmfcCt
PHWWGpqgPShPMwGwqJFTVtwtCVTCmTJcFc
qHqqSggLrRLBbvDDdndzRQ
WBddBQWZWWQqqQFMWfmrWsJnmVJJNDDVJGsLmHmLDN
PTgCjvCCPPPzSZGJVLsVZCHHnH
pzwtPTvzTjRTPtwSjPSzRgBbWMBfMwwZfbWrMrZFqFFM
BqDwVqdqlDlblQMf
ZcCWWcWzvJZjcPjZZZfTHfQJQHThqpMbQQJf
LPCcZcczZLgCjvPWgvstjsjmRRBdmGrdGdmSFGnFrtGmqr
CBvgQssVzfCBQSgvvvfmrlGrCtMGwthJlJtbrh
TpLqLRFpqdRpRTfNPtRmrMMtMlMMmlMJlt
PZTjqFFTHZZNZpqcVWzVvgzcWnSWfBDD
SVSTpgpVpdNbpcVdfjcNfbcJnqsltcJPvRJqRwQqlQsJls
zhWzDLmFHhmrWZmmzHJJQlnswqsvttrstQqs
zGtZFGGCmZmGGFhLBWBGGFdgVjgppMTSTgMfCNfVVSdj
CzjNJGcnzQJltPHttcPHTP
bLVsqLbLmSSVrqmdhVSmsVFFprfrFWrwTTWWWZpFPtlP
ssDsMqLqhvmvhdmdvzRCnQgRzzBjgnlNCM
TzTLzzSGRlRSjWzlWRzHGTpNhPhJPmdnNPPbhlbPbdhfPh
mBCDBVrCqVQvQMBcVcqBrBDsbtJfnZNbJndNNhthZNJfPZPs
wMCrqVvBzmzHTGLw
NbfwfZPPdVNPdBdQBcmQzrQz
nnWqHLWGFMDFDLDjsqnHLsrQGzmJczmQrgJmJGZmQrgJ
FFWRsHMHCZCWFwRwphpvlfTTpp
PclPlVZvLDNvVZSLSMvvDttmtfzFtzHqtqtzzccCFc
jrggQGhjQsTDbrbJjJQqzzCsdtzzFCdHqmBBHz
WGDgngwrQggZMNvMWPMRRV
wNgpMdMMcdSscccNcLLTbtQJtQJQltJwFtlBlzBt
HHGhrLrCvHWHCPhrWDtnBllnQbfQftGnfnBF
HvLjWCLHPZvHHHZjjrqVTTZVcppMgNNNNSpS
QQrwQmvWQjgTfvBjfffrSDcrqSqDDVLctqqcVd
GnHFnGhGplGMlHMNhzBzlLPLVcVNCPDqVNdcqLdqtV
GnMGpslMhGsRzzHzGsZFZQJTTmWfBbvfgfgJRfbwbW
MRCtSwMhvjCGtvMZDVWpVZJlVccNDlpb
gdLQFFwwLfHJWnQlcJJbWc
rdqdmqHLTLmsswsFHLFtMPRMCSSRtSjTPMPSCR
jmCCnLCLZjZjRjQTLZQhGPGhhzHhDRGRDzwzwh
stlJlrlJJcSSfSMMzPfhhGhzpwhpNwhD
rbrbBcSlWmdZWjDnTm
PNBRNnnqQRNfVfRtVVzgFLLttpSwgzzzmFFF
fcWlcbvvCFzLbwLw
rlrMrhTJhDcTTfhRNqHRQPQRQNQB
TrprpprRVVfpRpVqTVpzDdvmvbbCchhcttqcthSMdd
JlnZnFlsMBZnJHlsLsCLbSNtbNhdbbShCScm
FlZjjsHHsnQFQwTDzMRRpGRR
wHWzwCTTqJhzzvJhWHWhqJWrFsFQrrrFCfFfgjjgjprfsp
DBRmZRtZLbnRBGSBmtGSLpjBrrsfrgsTQVrVrrPrgr
DLnbcbtLtmNNmbRcGbcGmHzlThNNhqJTHdvqvWlHJh
GSNqjRcqflNLnCTTWrWn
BmwQtmtJwPwmzMwQtHtVssvrnpWTTnsTTgpVCLCs
DBBQHJJrzhzQDDfSljRfhccfcdZf
wtgtChCwzqgLzjggqtHtjFHHFcnPfdRDfZZVcPfVZZfGnfdm
vBTrRTTWGGmcTDVD
SJMbbpWslJblSSNzNsztRChzqRCj
gBHHCtVCSHMQlfFTQqCfmq
WrpdwjbwbwQGlPqSqblP
wWDncWrDDNdWNRjScScjpzvHZtBMZtJsvLVgvzssBsvs
VppWpVfmZPBlnmrGBzhttMzMpctLLcChSh
FwgLJvRdHcwMzSzjzc
QvbgdQLQgDvsqvqRHRDdDQDBWmBGBflnVbZmZmmnBBWrmW
SqShwLFCQGpDHCtZCWpW
bdHPHjTbJdsMnPHPbdjgtnBlVlBnVgtZpDBpWV
bdmPcjbjMNMvvHbTcQRNfRwRwLffwwqwNF
zdRHTpQTQHQnpnnQRHTsNNlJSJWmzJmJllNmSG
FBbRvLbFRwLqbbVgBVqqLFqJtJNcltsSGmgmGtNtgWmstm
FLhhfvvVwvjqfLRBqLVqbwqZQrTTpHMHjdrpnnDPDQCdCrpC
JgjzvbJCWgbjgGbJWjRhgNPGHHBMtqBStZZsHMSsBqtD
cfQdwQFdQQppnVVnlFLLBsBZMhqPlPMMqBSHDtHM
wnQhcnVddmdWgjvjmvRjjJ
QpcRtndvsLcVJtRSzWSlWjzSbjjWBv
qGZPqCTmGPqgGTCqHgCqZCPFWbbBNBMNBbdBMlWWrbjlMbFl
qhHDGhCmPhZHgDmDVQthttRchLwLdwcc
srpPMwlMmsrGFGswvDRhRWRDJJJchJ
fSgBbCBNnBTTgCNLTCRJhRJVWhTcVVVFFJdR
SbBnnLNZCLFQCZjnCnZFjPrzqmlMmmsrpzrlsmtt
BBsfDfsBDSWRwlLqmWCpWcllrl
nQMgMnnnhdntgMBrCdpNNLNlNqLqLl
FnQFHzPQJjJGRBGvfR
lRnVRFFlgMCRVwLgFZRnZQHWdcftHdmcJHmmMdzzfz
DGBqGQbhhBDbSBpGDBzqdNHJdtmcWdqdmtcm
bjbsBvjhSlVsPRgLQl
dDLbRdTMRJMbFRzZBfzNSjtNBzBD
PmgspqqVrppTVrvrsPhhfQwZBwNjNtNffzqqfwwN
mCcmsngrPvpVTssCVsvsPLRRJllGFlnRGbMJMWWlJJ
fGlGZHRRbwgPbZRRNCdcSWpncnQtQWlWcWpW
JrTLJgVvVLQQvtSvQncQ
JrrrmMTBVTmjBMrVjrshmJzgCfzRPCRZPGHfbwNPzbZHNH
qqqlDDZzVVnNqHDDFFFNlQpzjrTvsvzTbgJQQggjJp
cWPWcCmMfCMWdtPMhMbQQQjGGjpdvjTbjgjr
WtMSBCtCwchChMfBWtcPnNVNqZZLDRNqTRnnlwHn
mvQQnhBvhmvBmncmZBclTZTQccRFNFFdqFFgVqSRrgFrppNR
MjzJPzGPfffMCjVVjfPHLCFRNFStqrdRSdqdNGRqNptq
HDJHPjDJLfjbzfwPjCzCWWTwlmQhBnsWBvVsvBvZ
RVjcshhscQhrVjhvzjVfDNnzGtftmDHFttFGGf
qLcBCCMBJJbTdBDnNtdfnmDG
WpZgLLclTclRwgjgsrwsvj
shhhltNPcDtlNcNMcsctNtppLZvWWFLTFFZpTZDQgFLT
dRgJVzRHbqnLpTWQvLLJfp
mCVCdzqHndbqHCrVqRrmbwtNBsmPwNmScPgtPhBclw
bDDZMDrFPsrsMcsrbJZJdMMGpSzpSbwRSSRGpCHCGzlhCC
BWWNQjBLQVHhlGpSCmwj
ffwnNwfgtnNgVVwfNWBWnFsMJTJTcPFJcTFDsrJstJ
vQbQLQBpBvbvpHplHNTHWGZDngntZCQGgZhGhtjG
rqccPPmcrffRmsmCjVgnrGChChDjgW
fqRJsJMSlSzSWTbT
brsjjJPJwrJJsrRRlllNQGWQpwppCtfGGtWzGGMQ
ncBqqLTDnmLgVDZVnBDmdtVVtMzWWdMCQdpQWdVz
hDZgTSSnTzNPNFSFPF
VZVJJtWTsfTVVWsJhPWrCjzSBJlHSmjJCRlNSSlz
CqMpwccgvvgLnvLbMMRRjBNHzjmGmwNHlmlN
gLqqvpCDfVDrTfVW
CNMDGNPPNJCGbLnTffsTLT
tcBBRlrBdQrtmtWFjjbnrTjjFbjr
cTQQhcmvcBRcwDMVDZZPPCJh
mBCdgPLgZmLfGmfvGhtRQJWjtjQGQhtN
pMwrVwbwHMsqcTWQhQWzggTTWp
nnSMwrlrsmSZgvvmDd
WNSzpCzNzqzNdmqrRHrrLHFrJH
MtPfvnGMPnMcbnRtDHTRFFDrmJRQ
PcBsfPPHPGGfcSzZjNjpNZZdCs
mDCZVLDhWVSDCRvGtsGgGRHl
JjPwPNdcPnjPdcwNltHzzGmgGJzQJJRQ
dqfjnNmwmbmWrZMbMrThhB
qtBpNZFpBGFNfZNPmZPmQmHrmPPPTz
LLwJLvDvlWWLHdwDrVcCRcDVzzVVcV
sMMwvgjnMvjvnlsvNFBqfGHFqHGjtSpS
MmZZsFgwJTdTMdgmZdZRgFhDHhPQPPnRPhCrHhnnrPDD
fBcLlNNpQCDLDJJC
jSbWWlWpBpclWlWpNWlVBbWVdgwswFJmFJsGtdMggZFGbZwd
CMVQVMLLMFGRCMWQttnqqwQwhqsm
pJzlczSpPpPgmsqNhmPGDstq
gZgTccZGGpzdpjclGRVMVRFRMFvHRLRdLf
FMWMSBtStZqZWQtFtScWWSZmHPVJJVHwwlTgmgbzQwbwTJ
jhGLhdjNjsLvLsshzHJPVdVmmbzHzdHJ
jvDRNjnDNGRCzjLzZZpqnrFBSccWrMcB
zggmthDDghHvtrdgrVWfSBRwTHLWHwsBWw
PGGjpCjQnJQGJcJnnQpjFWVSsZWVLRZLBcsWSZBRWS
FGQlpnJCbqqGGRCjjnlCqGMtdNmmmvdNmmmzvhbrmgMz
TstvBTdgBhqTsdTcPlfCSrNMrNnrCNNSNNgp
HwLQwQDZzDjnDbmMhNSnmm
FZLVzLLQHRRzwWHjdPlJctlJtlsllhRs
fBtPsMDDswHvBmmVdBlSBRcGGnhVhg
LWJbrpFqpTLTTjqqNWlhnRGGSnhrcSdlRlsh
JWNbbpjJzTbNNNJNJMvmvfZHvzDsHDCsZw
LPGnPNLtwGhFFnJPfsqpVVszzpsP
TcWdvlrcWddggrDBDDdDMmWzRJqfVQZqmsfZsRQzZfZzQJ
TldWrMrDdlDCDdMTcwSLVCSShLNSwHjhGF
JGsWWWQsJmPwQWbBPmccbcbqFfMMpFDVCDFVFVCDqqfFwD
ZtLnlvLnNtvLndnCmfMVSmVCClfpVp
zTzZtjnZNLNmZvdtznntHHZJbBRGBRQWcJGbGsbsJRPQWT
MLmlMTPtQtMNlhbqbbqhflBB
rcrvjpSvScbRbBvbDBPG
ZZJzSHpzPrJzHFmMVMFmHCLNtV
  """
  println(Day3.getTotalScore(Day3.parseRucksacks(input)))
  println(Day3.getTotalBadgeScore(Day3.parseRucksacks(input)))
}