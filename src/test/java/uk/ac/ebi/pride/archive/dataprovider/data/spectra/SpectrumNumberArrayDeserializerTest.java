package uk.ac.ebi.pride.archive.dataprovider.data.spectra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SpectrumNumberArrayDeserializerTest{
    String spectraLine = "{\"usi\":\"mzspec:PRD000902:Rice_leaf_0h_phospho_test1:scan:3817:N[UNIMOD:7]NGSSIGS[UNIMOD:21]PGPGR/2\",\"spectraUsi\":\"mzspec:PXD002222:Rice_leaf_0h_phospho_test1:scan:3817\",\"assayAccession\":\"c0300332f12ec3420b966972b077cdb25520c0ba\",\"projectAccession\":\"PXD002222\",\"reanalysisAccession\":\"PRD000902\",\"proteinAccessions\":[\"Os07t0584500-01\"],\"peptideSequence\":\"NNGSSIGSPGPGR\",\"peptidoform\":\"N[UNIMOD:7]NGSSIGS[UNIMOD:21]PGPGR/2\",\"scores\":[{\"accession\":\"MS:1002257\",\"name\":\"Comet:expectation value\",\"value\":\"2.99E-8\"},{\"accession\":\"MS:1002354\",\"name\":\"PSM-level q-value\",\"value\":\"5.0E-6\"},{\"accession\":\"MS:1002357\",\"name\":\"PSM-level probability\",\"value\":\"1.0\"},{\"accession\":\"MS:1002355\",\"name\":\"PSM-level FDRScore\",\"value\":\"1.2510634038933093E-5\"}],\"sampleProperties\":[{\"accession\":\"EFO:0000324\",\"name\":\"cell type\",\"value\":\"not applicable\"},{\"accession\":\"OBI:0100026\",\"name\":\"organism\",\"value\":\"Oryza sativa\"},{\"accession\":\"EFO:0000635\",\"name\":\"organism part\",\"value\":\"Leaf\"},{\"accession\":\"EFO:0000408\",\"name\":\"disease\",\"value\":\"Xanthomonas oryzae pv. oryzae\"},{\"accession\":\"EFO:0002091\",\"name\":\"biological replicate\",\"value\":\"1\"}],\"isDecoy\":false,\"isValid\":true,\"precursorCharge\":2,\"precursorMz\":640.764810900142,\"bestSearchEngineScore\":{\"accession\":\"MS:1002354\",\"name\":\"PSM-level q-value\",\"value\":\"5.0E-6\"},\"numPeaks\":198,\"masses\":\"eJwllFtsVFUUhjdTi0JjiKiI5bbpxBdiH+TS2MbAYcolaRMCNTFC0Z5OKYp2gCktlpYpZ3pLsBRKJ/AwBjnTC2BJY2wjRAzkMBaMVDBgImGSMYdLjKQBgQolpeXSbz39WXuvy7/W+vdWShlNT+sN9QKbC0A34CnGbpmK7bRG5b7K7x9Hq8K3eRxVMF42jvZmf5C44mW12LF0C+zOAN08z2fYeQu5d1a0bR1HHX+fe5XVVg0mcvZSZ+b8Cvxn+74i/mb7bupO92Ob08p3kOfBujrsGXVf4D/p/xryDGWFyXOvgHtraBp1rDc64et42qvAkSrh9/IocfrRvK/FvsG9kYgXkt8Tp3/9LANezpREA3Ue+kq5H90TkPh+5mI/jpeA/3STx4z7TOp7T1DPWNgL6py78LQWT4Snzp5MXme5R3BlRhP+qxLwMvJypJ9V6Zy7+xuEX9uytfif6a3kvCvOPNz8ieQxYz72YxVkybxWH6Su830/fbsf+bDNI707qVNUXSdYB9r+uMSZtcKz9ALoNrxE38ahcuZifu4T/7IR6W9Lk+TZch50yuaDKuJnProiIXuK3tgAv9Bk0VttUnRS2bGd+52hasEe0NzqZa6qPEX4H07bAx4Iyzw3LkVf6suL9OP4W4izoz+S3/r2h0byBebiZwcuix5zU9YT13kN/uYKr+zN/hD9qaLwNvy7CrGN9hTqGh/3oUuVHxL9rW5EB3pRPfztRX2guSSAbu0LAy3Y/X+Cxm9j0velmMzrfCP7VtnNxLkfeNGt6b0OT+fKJtnXX5ky17+HmrEXn91I/ECgFfv3IGi80yFzessLTzv9E+ah36yhH/NWpeSZUS/22xH2aaQMloMjg+R356V+Cq9XFsi+R08Tp//1bsK+nYvunOGw3Kf+Ck91f6n0kbaNPs3hmOzjXoz5qjklzNH+r0POB/PZm/tkAv+QHg7Lu51Vg+719VzZa/9c/hWdLKE/4+oYujH/uMm71O9G8DcvJ0Ps8WqY+esrE9CfM5BGHv3LrF3wSBb7OV9QCB8dK8G2fhoj3jn1Kn669Rw6cfsyxT61n371mRjvzDnZhz71d4XUM4ty2bO1L5U4ozubc7f5XBH+Pcfln+g8DR+ryyv9H00KHlsj84sE2Y8T7RGdHcoU/8N36FtF7lPfKP1G/odAvui0uEPsg3dCErcGHbnRCP+FFX4PVDte34cdibFHtfs19q/rg/BzG36uMZ4DtkiqlQ==\",\"intensities\":\"eJwllHtMz3sYx7/Nco+Ve23tO8sOM3+g5fzyG9+jhMU5uS2X2Pc4OcOsMJEO85XNLUYZbc6vfJVbV7+Ewo6+1UmcTuYnlzL07WZGpJZx4o/j93r+evZ8nud5P+/n9lEURSlPyNe+C9Vxo9QrtdY119FjjGdeaTSHFKOv3+L2SssnvwA9xVXplbZnMfHK2sRH6N3Dm7AnT77M+7wR4Nnbh3wBb3NCEThNUbnYD8VcxL5vKbjmxgXnsccdvOuV+sSWWuI6vvJuLFwJrhr7C3aruwxe2tQ56GptGna7cl+H5P96CX226xx+peGFxDmyLoD389Zr5J3QCi+l+0/8lSfDsFvluehacC31WW9PuKVeP+zqlB9yJT70vvCOE36fMvGzI9rRDXVDFX6Xwp/g9+2yhZ/lIK+R7CP821rfIkOjpe6yPPpvhmTC01Y/taCn72ReyoNtMofo0+TT6/+ij9byHa/wC9qQA3//sIe8rzhwB3kytY48wZHgmPE1EnfVZ8BPXpwVzjbeXWnUre8wvyF3Ly9BPhxFPvXDO/D1gCp42vHvPNKHIPIpVh7xSoUbXa3OwN+uSrzKe8lKmU/dfPZDTYmROXycQb9UdzF9trv20EfrwS2Zu98smWPfdJlfeIX049Tvgn96Pf7asff0W4115vE+OBCeeuMq8R95QvZtdRR+SmEweEp7xz/I6psv8P/DHzzd7S/z3+JuAC9snPALHwMf5bccpBnpAc9w9ZLXCJhHPuP9SeE/ehB1W13lcg/t99kHLWia7N/QYu7CSveYyIGR8DL2K3Kf9X346Wdc7Il2pScLe3P8Pex1BczTPt9HvdqbtfRF60zl3UhpkP1w3mtErpsmc+339G/kxUHw1tc0Pgcvu1D6tPAA+Sz/X7GrWxfJnR9dBL6V1il+YZvgoSf1UofRlSH7l6AizVkR3KOZUijz/9wj+5DYJHOpSGKPNOd/3Jfhe5a8yt2Of8HxCRG8OzNlzpWOx9ivBb4m78C51KXHyd7by8bLfe3vesq7Y84V4goqaogLrEc3/HzJa28skf+uKKmauNSdveiTXsid3S6Cr5YdxN1rI9uJtwKGNoP3ciZztZccl/sfEyV7ED2YPhmZudLHYWN78G/olHk5jiCVjB+Rutmf/PoeJ/uozj7Mnui+6fTNCM3m/7VutYFnB3j4H/Tkg9Rv9y9jnlZpDfto7x0LHzMijr7oU3ZRv5bVwvy1Y74yzwn9aqRuHd5a8psq7X+A5tWt\",\"msLevel\":2,\"retentionTime\":753.2,\"missedCleavages\":0,\"modifications\":[{\"@type\":\"IdentifiedModification\",\"neutralLoss\":null,\"positionMap\":[{\"key\":1,\"value\":[{\"@type\":\"CvParam\",\"cvLabel\":\"MS\",\"accession\":\"MS:1003147\",\"name\":\"PTMProphet probability\",\"value\":\"0.3333\"}]}],\"modification\":{\"@type\":\"CvParam\",\"cvLabel\":\"UNIMOD\",\"accession\":\"UNIMOD:7\",\"name\":\"Deamidated\",\"value\":\"0.984016\"},\"attributes\":[]},{\"@type\":\"IdentifiedModification\",\"neutralLoss\":null,\"positionMap\":[{\"key\":8,\"value\":[{\"@type\":\"CvParam\",\"cvLabel\":\"MS\",\"accession\":\"MS:1003147\",\"name\":\"PTMProphet probability\",\"value\":\"0.774\"}]}],\"modification\":{\"@type\":\"CvParam\",\"cvLabel\":\"UNIMOD\",\"accession\":\"UNIMOD:21\",\"name\":\"Phospho\",\"value\":\"79.9663\"},\"attributes\":[]}],\"qualityEstimationMethods\":[{\"accession\":\"MS:1001194\",\"name\":\"quality estimation with decoy database\",\"value\":\"true\"}],\"properties\":[{\"accession\":\"PRIDE:0000511\",\"name\":\"Pass submitter threshold\",\"value\":\"true\"}]}";
    String arrayLine = "{\"usi\":\"mzspec:PRD000902:Rice_leaf_0h_phospho_test1:scan:3817:N[UNIMOD:7]NGSSIGS[UNIMOD:21]PGPGR/2\",\"spectraUsi\":\"mzspec:PXD002222:Rice_leaf_0h_phospho_test1:scan:3817\",\"assayAccession\":\"c0300332f12ec3420b966972b077cdb25520c0ba\",\"projectAccession\":\"PXD002222\",\"reanalysisAccession\":\"PRD000902\",\"proteinAccessions\":[\"Os07t0584500-01\"],\"peptideSequence\":\"NNGSSIGSPGPGR\",\"peptidoform\":\"N[UNIMOD:7]NGSSIGS[UNIMOD:21]PGPGR/2\",\"scores\":[{\"accession\":\"MS:1002257\",\"name\":\"Comet:expectation value\",\"value\":\"2.99E-8\"},{\"accession\":\"MS:1002354\",\"name\":\"PSM-level q-value\",\"value\":\"5.0E-6\"},{\"accession\":\"MS:1002357\",\"name\":\"PSM-level probability\",\"value\":\"1.0\"},{\"accession\":\"MS:1002355\",\"name\":\"PSM-level FDRScore\",\"value\":\"1.2510634038933093E-5\"}],\"sampleProperties\":[{\"accession\":\"EFO:0000324\",\"name\":\"cell type\",\"value\":\"not applicable\"},{\"accession\":\"OBI:0100026\",\"name\":\"organism\",\"value\":\"Oryza sativa\"},{\"accession\":\"EFO:0000635\",\"name\":\"organism part\",\"value\":\"Leaf\"},{\"accession\":\"EFO:0000408\",\"name\":\"disease\",\"value\":\"Xanthomonas oryzae pv. oryzae\"},{\"accession\":\"EFO:0002091\",\"name\":\"biological replicate\",\"value\":\"1\"}],\"isDecoy\":false,\"isValid\":true,\"precursorCharge\":2,\"precursorMz\":640.764810900142,\"bestSearchEngineScore\":{\"accession\":\"MS:1002354\",\"name\":\"PSM-level q-value\",\"value\":\"5.0E-6\"},\"numPeaks\":198,\"msLevel\":2,\"retentionTime\":753.2,\"missedCleavages\":0,\"modifications\":[{\"@type\":\"IdentifiedModification\",\"neutralLoss\":null,\"positionMap\":[{\"key\":1,\"value\":[{\"@type\":\"CvParam\",\"cvLabel\":\"MS\",\"accession\":\"MS:1003147\",\"name\":\"PTMProphet probability\",\"value\":\"0.3333\"}]}],\"modification\":{\"@type\":\"CvParam\",\"cvLabel\":\"UNIMOD\",\"accession\":\"UNIMOD:7\",\"name\":\"Deamidated\",\"value\":\"0.984016\"},\"attributes\":[]},{\"@type\":\"IdentifiedModification\",\"neutralLoss\":null,\"positionMap\":[{\"key\":8,\"value\":[{\"@type\":\"CvParam\",\"cvLabel\":\"MS\",\"accession\":\"MS:1003147\",\"name\":\"PTMProphet probability\",\"value\":\"0.774\"}]}],\"modification\":{\"@type\":\"CvParam\",\"cvLabel\":\"UNIMOD\",\"accession\":\"UNIMOD:21\",\"name\":\"Phospho\",\"value\":\"79.9663\"},\"attributes\":[]}],\"qualityEstimationMethods\":[{\"accession\":\"MS:1001194\",\"name\":\"quality estimation with decoy database\",\"value\":\"true\"}],\"properties\":[{\"accession\":\"PRIDE:0000511\",\"name\":\"Pass submitter threshold\",\"value\":\"true\"}],\"masses\":[639.8155517578125,618.3179931640625,136.07591247558594,610.3189697265625,627.3206787109375,147.07717895507812,242.07672119140625,230.0762939453125,278.1520080566406,484.2738952636719,515.3289184570312,516.3333129882812,184.07164001464844,483.2682189941406,265.14227294921875,519.0947875976562,425.13818359375,967.3564453125,323.13189697265625,372.1324768066406,820.3612670898438,374.1309814453125,407.1304626464844,587.3685302734375,212.0658416748047,447.2523498535156,550.86865234375,586.3673095703125,514.868896484375,229.12750244140625,394.12567138671875,527.1224365234375,447.7519226074219,837.3701782226562,398.1269226074219,111.04457092285156,632.2511596679688,322.1872863769531,667.2576904296875,170.0924072265625,236.4058074951172,246.15504455566406,158.0928497314453,394.6813049316406,130.0862274169922,534.767333984375,534.2755126953125,573.7783813476562,576.780517578125,641.2778930664062,640.2840576171875,708.2850341796875,859.2882080078125,583.2862548828125,707.288330078125,110.07140350341797,101.0714340209961,347.17156982421875,214.08274841308594,640.7870483398438,276.1643981933594,582.790283203125,562.7892456054688,444.29290771484375,552.2924194335938,459.1656188964844,591.2965087890625,592.2965087890625,600.2981567382812,591.7970581054688,601.2993774414062,232.14122009277344,327.16339111328125,584.3009643554688,543.3030395507812,592.8042602539062,600.8043212890625,582.3031005859375,155.0804443359375,571.3067016601562,167.08132934570312,609.311279296875,493.84344482421875,362.21624755859375,423.84283447265625,426.84283447265625,386.2149353027344,432.21533203125,897.4530639648438,560.1981811523438,260.0875244140625,460.838623046875,424.83660888671875,630.7005004882812,694.202392578125,258.08917236328125,269.0892639160156,112.05097198486328,539.205078125,129.1023406982422,810.453369140625,312.085693359375,811.4558715820312,896.4542236328125,379.20904541015625,439.830810546875,244.166015625,360.2004699707031,363.2005920410156,406.8279724121094,953.4739379882812,954.4708862304688,639.7232666015625,596.2259521484375,848.7218017578125,360.701416015625,225.10118103027344,270.0711364746094,557.2279052734375,581.7296752929688,894.4837646484375,182.0388641357422,1051.448486328125,1052.4495849609375,442.19659423828125,370.1933288574219,421.8190002441406,311.6926574707031,590.7365112304688,312.19427490234375,329.1936950683594,302.68829345703125,893.4971923828125,120.08074188232422,323.189453125,599.7482299804688,201.12208557128906,210.1222381591797,328.12298583984375,568.8776245117188,356.121337890625,622.3807983398438,426.1228942871094,133.0606231689453,908.39013671875,426.2460021972656,452.7450256347656,136.06199645996094,228.06141662597656,719.3895263671875,452.2424621582031,185.05526733398438,438.2414855957031,495.86505126953125,175.1190643310547,722.3965454101562,477.8636169433594,408.1151123046875,152.05690002441406,448.73724365234375,497.8601379394531,147.0582275390625,907.4002685546875,147.11341857910156,479.8577880859375,496.85809326171875,115.08692169189453,498.8564147949219,329.1079406738281,282.1089172363281,395.23236083984375,405.7287902832031,936.4215698242188,112.08688354492188,403.60406494140625,127.08666229248047,394.72991943359375,459.8526306152344,450.2279052734375,461.8534851074219,357.1037292480469,311.0986022949219,442.85028076171875,450.850830078125,494.8514404296875,287.098388671875,723.4244384765625,809.4286499023438,724.4224853515625,478.8493347167969,261.100830078125,345.2253112792969,339.0941162109375,994.43408203125,213.04925537109375,289.095947265625,391.0945129394531,443.7199401855469],\"intensities\":[3383.359375,5046.22314453125,5469.8837890625,24065.251953125,3858.4365234375,4208.39501953125,3541.00341796875,9016.9541015625,3368.909912109375,18877.46875,25663.779296875,3093.734619140625,5395.27734375,98487.3046875,3767.2119140625,2467.42529296875,2856.265625,3497.753662109375,2596.706298828125,11534.724609375,12226.3154296875,2685.9482421875,3116.6494140625,11422.6806640625,4279.9443359375,11396.384765625,3141.888916015625,44046.05859375,3069.638671875,2254.124755859375,3613.35009765625,2766.61279296875,5233.306640625,3825.073486328125,3021.970703125,2695.416015625,3025.86083984375,19228.47265625,4240.9169921875,2694.68359375,2451.588134765625,12904.2939453125,3118.18115234375,4247.95751953125,3186.132568359375,9424.25390625,21738.619140625,8529.974609375,3740.875244140625,11266.94140625,55063.09765625,11926.4072265625,3924.8583984375,5271.14306640625,36777.02734375,4983.56884765625,8933.609375,4246.29638671875,3550.901123046875,31193.33984375,2356.053955078125,16600.26953125,7814.3466796875,14085.154296875,4932.11767578125,3554.696533203125,188470.34375,39394.76171875,18990.447265625,117255.3984375,4437.48583984375,4117.81396484375,2421.965087890625,3937.035888671875,18349.576171875,16493.44921875,19111.0,17090.984375,2377.381103515625,4719.76123046875,2988.845703125,99468.7421875,3752.238525390625,9327.875,2775.343994140625,3134.968505859375,11637.615234375,3615.02685546875,5171.9794921875,5055.22900390625,2355.291015625,3122.794677734375,22454.20703125,3230.170166015625,3853.021240234375,4954.84521484375,2632.0390625,21785.43359375,5150.671875,13771.78125,29415.046875,3080.740966796875,4113.68896484375,20161.75,2700.607177734375,3595.614990234375,3665.193359375,22341.072265625,3322.3056640625,4935.072265625,11559.85546875,2948.54443359375,3804.46826171875,8595.787109375,2969.05322265625,2903.0234375,2152.78466796875,5188.0341796875,5376.513671875,3069.400634765625,12089.2060546875,1916.671142578125,12489.74609375,3541.896728515625,3965.322021484375,2351.45361328125,3521.46142578125,31565.9140625,25376.947265625,4402.38525390625,10668.0263671875,3204.824951171875,27489.458984375,2646.308837890625,12044.599609375,3248.53466796875,3110.720947265625,2342.7734375,2678.27294921875,12500.427734375,2938.229248046875,16971.7421875,16513.73046875,2209.622314453125,3030.239501953125,4850.96826171875,5081.43359375,17878.984375,2556.61767578125,12094.04296875,14795.546875,16528.02734375,3101.87255859375,20711.01953125,45269.625,24852.130859375,16601.443359375,11332.6630859375,23486.048828125,4001.112060546875,11135.337890625,4070.55078125,17428.2109375,4273.41357421875,9963.357421875,83832.1328125,4316.1630859375,4781.73193359375,12855.236328125,4082.03955078125,31792.29296875,3741.931640625,3143.661865234375,3235.0458984375,2309.1474609375,12103.18359375,74112.921875,3574.412109375,3525.113037109375,3484.28515625,9741.2529296875,13432.9794921875,8466.4931640625,3016.510498046875,3407.1025390625,12231.458984375,36488.58984375,25117.880859375,10606.0537109375,22294.80859375,3084.249755859375,16753.056640625,10994.3544921875,4577.6142578125,3074.77587890625,10758.2890625,3248.254150390625,9680.939453125]}";
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @Ignore
    public void testDeserialize() {
        try {
            BinaryArchiveSpectrum spectrum = objectMapper.readValue(spectraLine, BinaryArchiveSpectrum.class);
            Assert.assertEquals("mzspec:PRD000902:Rice_leaf_0h_phospho_test1:scan:3817:N[UNIMOD:7]NGSSIGS[UNIMOD:21]PGPGR/2", spectrum.getUsi());

            ArchiveSpectrum nonBinSpec = new ArchiveSpectrum(spectrum);

            String line = objectMapper.writeValueAsString(nonBinSpec);
            Assert.assertEquals(line, arrayLine);

            ArchiveSpectrum archiveSpectrum = objectMapper.readValue(arrayLine, ArchiveSpectrum.class);
            Assert.assertEquals("mzspec:PRD000902:Rice_leaf_0h_phospho_test1:scan:3817:N[UNIMOD:7]NGSSIGS[UNIMOD:21]PGPGR/2", archiveSpectrum.getUsi());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}