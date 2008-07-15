package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RegexTokenDefinition extends GenASTNode {
  public RegexTokenDefinition(StateSet stateSet, REKind rEKind, CaseFlag caseFlag, REList rEList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<StateSet>("stateSet", stateSet),
      new PropertyOne<REKind>("rEKind", rEKind),
      new PropertyZeroOrOne<CaseFlag>("caseFlag", caseFlag),
      new PropertyOne<REList>("rEList", rEList)
    }, firstToken, lastToken);
  }
  public RegexTokenDefinition(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RegexTokenDefinition(cloneProperties(),firstToken,lastToken);
  }
  public StateSet getStateSet() {
    return ((PropertyZeroOrOne<StateSet>)getProperty("stateSet")).getValue();
  }
  public REKind getREKind() {
    return ((PropertyOne<REKind>)getProperty("rEKind")).getValue();
  }
  public CaseFlag getCaseFlag() {
    return ((PropertyZeroOrOne<CaseFlag>)getProperty("caseFlag")).getValue();
  }
  public REList getREList() {
    return ((PropertyOne<REList>)getProperty("rEList")).getValue();
  }
}
