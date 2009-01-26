package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class arglist1EndEnd2 extends arglist1EndEnd {
  public arglist1EndEnd2(ExtraKeywordValueList extraKeywordValueList1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ExtraKeywordValueList>("extraKeywordValueList1", extraKeywordValueList1)
    }, firstToken, lastToken);
  }
  public arglist1EndEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new arglist1EndEnd2(cloneProperties(),firstToken,lastToken);
  }
  public ExtraKeywordValueList getExtraKeywordValueList1() {
    return ((PropertyOne<ExtraKeywordValueList>)getProperty("extraKeywordValueList1")).getValue();
  }
}
