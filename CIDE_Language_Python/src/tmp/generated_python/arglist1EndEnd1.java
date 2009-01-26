package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class arglist1EndEnd1 extends arglist1EndEnd {
  public arglist1EndEnd1(ExtraArgValueList extraArgValueList, ExtraKeywordValueList extraKeywordValueList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ExtraArgValueList>("extraArgValueList", extraArgValueList),
      new PropertyZeroOrOne<ExtraKeywordValueList>("extraKeywordValueList", extraKeywordValueList)
    }, firstToken, lastToken);
  }
  public arglist1EndEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new arglist1EndEnd1(cloneProperties(),firstToken,lastToken);
  }
  public ExtraArgValueList getExtraArgValueList() {
    return ((PropertyOne<ExtraArgValueList>)getProperty("extraArgValueList")).getValue();
  }
  public ExtraKeywordValueList getExtraKeywordValueList() {
    return ((PropertyZeroOrOne<ExtraKeywordValueList>)getProperty("extraKeywordValueList")).getValue();
  }
}
