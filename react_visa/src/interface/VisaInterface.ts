import { VisaDocumentInteface } from "./VisaDocumentInterface";
import { VisaPerkInterface } from "./VisaPerkInterface";
import { VisaRequirementInterface } from "./VisaRequirementInterface";
import { VisaStepInterface } from "./VisaStepInterface";

export interface VisaInterface {
    visaId: number,
    name: string,
    processTimeInDays: number,
    feesLow: number,
    feesHigh: number,
    countryCode: string,
    region: string,
    gdpRank: number,
    hasPerks: boolean,
    hasRoadToCitizenship: boolean,
    // Optional fields for future use
    documents?: Array<VisaDocumentInteface>,
    steps?: Array<VisaStepInterface>,
    requirements?: Array<VisaRequirementInterface>,
    perks?: Array<VisaPerkInterface>
}
