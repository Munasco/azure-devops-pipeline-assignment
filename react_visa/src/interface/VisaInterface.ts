import { VisaDocumentInteface } from "./VisaDocumentInterface";
import { VisaPerkInterface } from "./VisaPerkInterface";
import { VisaRequirementInterface } from "./VisaRequirementInterface";
import { VisaStepInterface } from "./VisaStepInterface";

export interface VisaInterface {
    visaId: number,
    name: string,
    processTimeInDays: number,
    fees: number,
    country_code: string,
    region: string,
    gdpRank: number,
    documents: Array<VisaDocumentInteface>,
    steps: Array<VisaStepInterface>,
    requirements: Array<VisaRequirementInterface>,
    perks: Array<VisaPerkInterface>
}