package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Children extends GenASTNode {
  public Children(SeqOrChoice seqOrChoice, Modifier modifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SeqOrChoice>("seqOrChoice", seqOrChoice),
      new PropertyZeroOrOne<Modifier>("modifier", modifier)
    }, firstToken, lastToken);
  }
  public Children(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Children(cloneProperties(),firstToken,lastToken);
  }
  public SeqOrChoice getSeqOrChoice() {
    return ((PropertyOne<SeqOrChoice>)getProperty("seqOrChoice")).getValue();
  }
  public Modifier getModifier() {
    return ((PropertyZeroOrOne<Modifier>)getProperty("modifier")).getValue();
  }
}
