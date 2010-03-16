/* Generated By:JavaCC: Do not edit this line. ManifestParser.java */
package tmp.generated_manifest;

import java.io.*;
import java.util.*;
import cide.gast.*;
import cide.gparser.*;

public class ManifestParser implements ManifestParserConstants {

  final public File File() throws ParseException {
        Line line;
        ArrayList<Line> lineList = new ArrayList<Line>();
        Token t;
        ASTStringNode eof;
        Token firstToken=token;
    label_1:
    while (true) {
      line = Line();
                     lineList.add(line);
      switch (jj_nt.kind) {
      case EXPORTPACKAGE:
      case NAME:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
    }
    t = jj_consume_token(0);
                                                    eof=new ASTStringNode(t.image,new WToken(t));
         {if (true) return new File(lineList, eof, firstToken.next,token);}
    throw new Error("Missing return statement in function");
  }

  final public Line Line() throws ParseException {
        Header header;
        Export export;
        Token firstToken=token;
    switch (jj_nt.kind) {
    case NAME:
      header = Header();
         {if (true) return new Line1(header, firstToken.next,token);}
      break;
    case EXPORTPACKAGE:
      export = Export();
         {if (true) return new Line2(export, firstToken.next,token);}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Header Header() throws ParseException {
        Token t;
        ASTStringNode name;
        ASTStringNode value;
        ASTStringNode value1;
        ArrayList<ASTStringNode> value1List = new ArrayList<ASTStringNode>();
        Token firstToken=token;
    t = jj_consume_token(NAME);
                 name=new ASTStringNode(t.image,new WToken(t));
    jj_consume_token(COLON);
    t = jj_consume_token(VALUE);
                                                                               value=new ASTStringNode(t.image,new WToken(t));
    label_2:
    while (true) {
      switch (jj_nt.kind) {
      case VALUE:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      t = jj_consume_token(VALUE);
                                                                                                                                           value1=new ASTStringNode(t.image,new WToken(t));
                                                                                                                                                                                             value1List.add(value1);
    }
         {if (true) return new Header(name, value, value1List, firstToken.next,token);}
    throw new Error("Missing return statement in function");
  }

  final public Export Export() throws ParseException {
        Token t;
        ASTStringNode packagedcl;
        ArrayList<ASTStringNode> list0=new ArrayList<ASTStringNode>();
        ASTStringNode packagedcl1;
        ArrayList<ASTStringNode> packagedcl1List = new ArrayList<ASTStringNode>();
        ASTStringNode packageend;
        Token firstToken=token;
    jj_consume_token(EXPORTPACKAGE);
    t = jj_consume_token(PACKAGEDCL);
                                          packagedcl=new ASTStringNode(t.image,new WToken(t));
                                                                                                list0.add(packagedcl);
    label_3:
    while (true) {
      switch (jj_nt.kind) {
      case 12:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      jj_consume_token(12);
      t = jj_consume_token(PACKAGEDCL);
                                                                                                                                            packagedcl1=new ASTStringNode(t.image,new WToken(t));
                                                                                                                                                                                                   list0.add(packagedcl1);
    }
    t = jj_consume_token(PACKAGEEND);
                                                                                                                                                                                                                                             packageend=new ASTStringNode(t.image,new WToken(t));
         {if (true) return new Export(list0, packageend, firstToken.next,token);}
    throw new Error("Missing return statement in function");
  }

  public ManifestParserTokenManager token_source;
  public Token token, jj_nt;
  private int jj_gen;
  final private int[] jj_la1 = new int[4];
  static private int[] jj_la1_0;
  static {
      jj_la1_0();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x60,0x60,0x100,0x8000,};
   }

  public ManifestParser(CharStream stream) {
    token_source = new ManifestParserTokenManager(stream);
    token = new Token();
    token.next = jj_nt = token_source.getNextToken();
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  public void ReInit(CharStream stream) {
    token_source.ReInit(stream);
    token = new Token();
    token.next = jj_nt = token_source.getNextToken();
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  public ManifestParser(ManifestParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    token.next = jj_nt = token_source.getNextToken();
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  public void ReInit(ManifestParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    token.next = jj_nt = token_source.getNextToken();
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken = token;
    if ((token = jj_nt).next != null) jj_nt = jj_nt.next;
    else jj_nt = jj_nt.next = token_source.getNextToken();
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    jj_nt = token;
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  final public Token getNextToken() {
    if ((token = jj_nt).next != null) jj_nt = jj_nt.next;
    else jj_nt = jj_nt.next = token_source.getNextToken();
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[16];
    for (int i = 0; i < 16; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 4; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 16; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

}