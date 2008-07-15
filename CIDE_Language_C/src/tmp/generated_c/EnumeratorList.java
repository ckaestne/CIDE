package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumeratorList extends GenASTNode {
  public EnumeratorList(Enumerator enumerator, ArrayList<Enumerator> enumerator1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Enumerator>("enumerator", enumerator),
      new PropertyZeroOrMore<Enumerator>("enumerator1", enumerator1)
    }, firstToken, lastToken);
  }
  public EnumeratorList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EnumeratorList(cloneProperties(),firstToken,lastToken);
  }
  public Enumerator getEnumerator() {
    return ((PropertyOne<Enumerator>)getProperty("enumerator")).getValue();
  }
  public ArrayList<Enumerator> getEnumerator1() {
    return ((PropertyZeroOrMore<Enumerator>)getProperty("enumerator1")).getValue();
  }
}
